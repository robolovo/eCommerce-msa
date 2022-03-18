package com.example.order.service;

import com.example.order.controller.dto.OrderRequest;
import com.example.order.domain.Order;
import com.example.order.domain.OrderRepository;
import com.example.order.infrastructure.kafka.KafkaProducer;
import com.example.order.service.dto.OrderKafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    public void createOrder(Long userId, OrderRequest orderRequest) {
        Order order = orderRequest.toOrder(userId);
        OrderKafkaMessage orderKafkaMessage = order.toKafkaMessage(userId);

        kafkaProducer.send("product-topic", orderKafkaMessage);
        kafkaProducer.send("user-topic", orderKafkaMessage);

        orderRepository.save(order);
    }
}
