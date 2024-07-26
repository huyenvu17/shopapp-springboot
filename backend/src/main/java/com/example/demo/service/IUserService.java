package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.DataNotFoundException;

public interface IUserService{
    UserEntity createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
}
