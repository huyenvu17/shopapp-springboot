package com.example.demo.service;

import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.OrderDetailEntity;
import com.example.demo.exception.DataNotFoundException;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailEntity createOrderDetail(OrderDetailDTO newOrderDetail) throws Exception;
    OrderDetailEntity getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetailEntity updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData)
            throws DataNotFoundException;
    void deleteById(Long id);
    List<OrderDetailEntity> findByOrderId(Long orderId);
}
