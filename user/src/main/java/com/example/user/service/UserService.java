package com.example.user.service;

import com.example.user.controller.dto.SignUpRequest;
import com.example.user.domain.User;
import com.example.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest signUpRequest) {
        User user = signUpRequest.toUser();
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
