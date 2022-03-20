package com.example.user.domain.vo;

import com.example.user.exception.ErrorCode;
import com.example.user.exception.NotEnoughMoneyException;

import javax.persistence.Embeddable;

import static com.example.user.exception.ErrorCode.NOT_ENOUGH_MONEY;

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
        } else {
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY);
        }
    }
}
