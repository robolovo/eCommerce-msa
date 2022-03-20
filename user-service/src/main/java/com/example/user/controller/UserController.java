package com.example.user.controller;

import com.example.user.controller.dto.PayRequest;
import com.example.user.controller.dto.SignUpRequest;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user-service")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/payments")
    public ResponseEntity<Void> pay(@RequestBody PayRequest payRequest) {
        userService.pay(payRequest);
        return ResponseEntity.ok().build();
    }
}
