package com.example.order.controller.dto;

import com.example.order.domain.Order;
import com.example.order.service.dto.OrderKafkaMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderRequest {

    private Long productId;

    private Long userId;

    private int quantity;

    private int unitPrice;

    public OrderRequest(Long productId, Long userId, int quantity, int unitPrice) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Order toOrder() {
        return Order.builder()
                .productId(productId)
                .userId(userId)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .totalPrice(unitPrice * quantity)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderKafkaMessage toKafkaMessage() {
        return new OrderKafkaMessage(userId, productId, quantity, unitPrice);
    }
}
