package com.example.order.service;

import com.example.order.controller.dto.OrderRequest;
import com.example.order.domain.OrderRepository;
import com.example.order.infrastructure.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final KafkaProducer kafkaProducer;

    public void createOrder(OrderRequest orderRequest) {
        RequestEntity<Map<String, Object>> productRequest = productServiceRequest(orderRequest);
        RequestEntity<Map<String, Object>> paymentRequest = paymentServiceRequest(orderRequest);

        try {
            restTemplate.exchange(productRequest, Object.class);
        } catch (RuntimeException e) {
            log.info("{}", e.getMessage());
            return;
        }

        try {
            restTemplate.exchange(paymentRequest, Object.class);
        } catch (RuntimeException e) {
            log.info("{}", e.getMessage());
            kafkaProducer.send("product-topic", orderRequest.toKafkaMessage());
            return;
        }

        orderRepository.save(orderRequest.toOrder());
    }

    private RequestEntity<Map<String, Object>> productServiceRequest(OrderRequest orderRequest) {
        String requestURL = "http://localhost:8000/product-service/products";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("productId", orderRequest.getProductId());
        requestBody.put("quantity", orderRequest.getQuantity());

        return RequestEntity
                .put(URI.create(requestURL))
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);
    }

    private RequestEntity<Map<String, Object>> paymentServiceRequest(OrderRequest orderRequest) {
        String requestURL = "http://localhost:8000/user-service/users/payments";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", orderRequest.getUserId());
        requestBody.put("totalPrice", orderRequest.getQuantity() * orderRequest.getUnitPrice());

        return RequestEntity
                .post(URI.create(requestURL))
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);
    }
}
