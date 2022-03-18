package com.example.order.controller.dto;

import com.example.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderRequest {

    private Long productId;

    private int quantity;

    private int unitPrice;

    public OrderRequest(Long productId, int quantity, int unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Order toOrder(Long userId) {
        return Order.builder()
                .productId(productId)
                .userId(userId)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .totalPrice(unitPrice * quantity)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
