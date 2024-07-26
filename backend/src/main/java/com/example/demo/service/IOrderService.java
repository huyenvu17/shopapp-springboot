package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOrderService {
    OrderEntity createOrder(OrderDTO orderDTO) throws Exception;
    OrderEntity getOrder(Long id);
    List<OrderEntity> findByUserId(Long userid);
    OrderEntity updateOrder(Long id, OrderDTO orderDTO) throws Exception;
    void deleteOrder(Long id);
}
