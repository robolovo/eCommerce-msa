package com.example.order.domain;

import com.example.order.service.dto.OrderKafkaMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "orders")
@Getter
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long userId;

    private int quantity;

    private int unitPrice;

    private int totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Order(
            Long id,
            Long productId,
            Long userId,
            int quantity,
            int unitPrice,
            int totalPrice,
            LocalDateTime createdAt)
    {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

}
