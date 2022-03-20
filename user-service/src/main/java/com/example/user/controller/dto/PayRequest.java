package com.example.user.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PayRequest {

    private Long userId;

    private int totalPrice;

    public PayRequest(Long userId, int totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
