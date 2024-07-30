package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }
}
