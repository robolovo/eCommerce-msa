package com.example.order.controller;

import com.example.order.controller.dto.OrderRequest;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/order-service")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestParam("user") Long userId,
                                            @RequestBody OrderRequest orderRequest) {
        orderService.createOrder(userId, orderRequest);
        return ResponseEntity.ok().build();
    }
}
