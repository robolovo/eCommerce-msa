package com.example.product.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubtractStockRequest {

    private Long productId;

    private int quantity;

    public SubtractStockRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
