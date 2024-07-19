package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<OrderEntity, Long> {
    //Tìm các đơn hàng của 1 user nào đó
    List<OrderEntity> findByUserId(Long userId);
}
