package com.example.product.domain;

import com.example.product.exception.ErrorCode;
import com.example.product.exception.OutOfStockException;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.example.product.exception.ErrorCode.OUT_OF_STOCK;

@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stockQuantity;

    private int unitPrice;

    public Product(Long id, String name, int stockQuantity, int unitPrice) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
    }

    public Product(String name, int stockQuantity, int unitPrice) {
        this(null, name, stockQuantity, unitPrice);
    }

    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    public void subtractStockQuantity(int quantity) {
        if (stockQuantity - quantity < 0) {
            throw new OutOfStockException(OUT_OF_STOCK);
        }
        this.stockQuantity -= quantity;
    }
}
