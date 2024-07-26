package com.example.demo.service;

import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.OrderDetailEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    @Override
    public OrderDetailEntity createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        //tìm xem orderId có tồn tại ko
        OrderEntity order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find Order with id : "+orderDetailDTO.getOrderId()));
        // Tìm Product theo id
        ProductEntity product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id: " + orderDetailDTO.getProductId()));
        OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .price(orderDetailDTO.getPrice())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();
        //lưu vào db
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetailEntity getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Cannot find OrderDetail with id: "+id));
    }

    @Override
    public OrderDetailEntity updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO)
            throws DataNotFoundException {
        //tìm xem order detail có tồn tại ko đã
        OrderDetailEntity existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order detail with id: "+id));
        OrderEntity existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: "+id));
        ProductEntity existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id: " + orderDetailDTO.getProductId()));
        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailEntity> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
