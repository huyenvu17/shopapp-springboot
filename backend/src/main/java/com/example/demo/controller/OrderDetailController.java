package com.example.demo.controller;

import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.OrderDetailEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.responses.OrderDetailResponse;
import com.example.demo.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    //Thêm mới 1 order detail
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid  @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            OrderDetailEntity newOrderDetail = orderDetailService.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(newOrderDetail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("id") Long id) throws DataNotFoundException {
        try {
            OrderDetailEntity orderDetail = orderDetailService.getOrderDetail(id);
            return ResponseEntity.ok().body(OrderDetailResponse.fromOrderDetail(orderDetail));
            //return ResponseEntity.ok(orderDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid @PathVariable("orderId") Long orderId
    ) {
        List<OrderDetailEntity> orderDetails = orderDetailService.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = orderDetails
                .stream()
                .map(OrderDetailResponse::fromOrderDetail)
                .toList();
        return ResponseEntity.ok(orderDetailResponses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            OrderDetailEntity orderDetail = orderDetailService.updateOrderDetail(id, orderDetailDTO);
            return ResponseEntity.ok().body(orderDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid @PathVariable("id") Long id) {
        orderDetailService.deleteById(id);
        return ResponseEntity.ok().body("Delete Order detail with id : "+id+" successfully");
        //return ResponseEntity.noContent().build();
    }
}
