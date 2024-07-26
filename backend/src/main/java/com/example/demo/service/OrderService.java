package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Override
    public OrderEntity createOrder(OrderDTO orderDTO) throws Exception {
        UserEntity user = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found."));
        modelMapper.typeMap(OrderDTO.class, OrderEntity.class)
                .addMappings(mapper -> mapper.skip(OrderEntity::setId));
        OrderEntity order = new OrderEntity();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if(shippingDate.isBefore(LocalDate.now())){
            throw new DataNotFoundException("\"Date must be at least today !\"");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return order;
    }
    @Override
    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderEntity> findByUserId(Long userid) {
        return orderRepository.findByUserId(userid);
    }

    @Override
    public OrderEntity updateOrder(Long id, OrderDTO orderDTO) throws Exception {
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found."));
        UserEntity existingUser = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found."));
        modelMapper.typeMap(OrderDTO.class, OrderEntity.class)
                .addMappings(mapper -> mapper.skip(OrderEntity::setId));
        modelMapper.map(orderDTO, order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        //no hard-delete, => please soft-delete
        if(order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }
    }
}
