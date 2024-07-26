package com.example.demo.repository;
import com.example.demo.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findByOrderId(Long orderId);
}