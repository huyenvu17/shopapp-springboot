package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
