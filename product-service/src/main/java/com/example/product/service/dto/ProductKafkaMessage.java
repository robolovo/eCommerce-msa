package com.example.product.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductKafkaMessage {

    private Long productId;

    private Long userId;

    private int quantity;

    private int unitPrice;

    private String state;

    public ProductKafkaMessage(Long productId, Long userId, int quantity, int unitPrice, String state) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.state = state;
    }
}
