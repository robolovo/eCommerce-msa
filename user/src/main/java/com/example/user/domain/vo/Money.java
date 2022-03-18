package com.example.user.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Money {

    private int value;

    public Money() { }

    private Money(int value) {
        this.value = value;
    }

    public static Money init() {
        return new Money(10_000);
    }

    public void subtract(int totalPrice) {
        if (this.value - totalPrice >= 0) {
            this.value -= totalPrice;
        }
    }
}
