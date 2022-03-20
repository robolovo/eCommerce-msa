package com.example.user.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserKafkaMessage {

    private Long productId;

    private Long userId;

    private int quantity;

    private int unitPrice;

    private String state;

    public UserKafkaMessage(Long productId, Long userId, int quantity, int unitPrice, String state) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.state = state;
    }
}
