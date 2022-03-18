package com.example.product.infrastructure.kafka;

import com.example.product.domain.Product;
import com.example.product.domain.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Transactional
    @KafkaListener(topics = "product-topic")
    public void receive(String message) {
        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product product = productRepository.findById(Long.parseLong(map.get("productId")))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        product.subtractStockQuantity(Integer.parseInt(map.get("quantity")));
    }
}
