package com.example.demo.controller;

import com.example.demo.entity.RoleEntity;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @GetMapping("")
    public ResponseEntity<?> getRoles() {
        List<RoleEntity> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
