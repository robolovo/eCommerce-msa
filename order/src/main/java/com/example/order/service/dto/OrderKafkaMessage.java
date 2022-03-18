package com.example.order.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderKafkaMessage {

    private Long userId;

    private Long productId;

    private int quantity;

    private int unitPrice;

    public OrderKafkaMessage(Long userId, Long productId, int quantity, int unitPrice) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
