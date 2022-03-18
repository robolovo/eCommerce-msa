package com.example.product.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public void subtractStockQuantity(int quantity) {
        this.stockQuantity -= quantity;
    }
}
