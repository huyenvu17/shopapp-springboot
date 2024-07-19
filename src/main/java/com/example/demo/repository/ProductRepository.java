package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
    Page<ProductEntity> findAll(Pageable pageable);//phân trang
}
