package com.example.user.infrastructure.kafka;

import com.example.user.domain.User;
import com.example.user.domain.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Transactional
    @KafkaListener(topics = "user-topic")
    public void receive(String message) {
        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        User user = userRepository.findById(Long.parseLong(map.get("userId")))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        int totalPrice = Integer.parseInt(map.get("quantity")) * Integer.parseInt(map.get("unitPrice"));
        user.subtractMoney(totalPrice);
    }
}
