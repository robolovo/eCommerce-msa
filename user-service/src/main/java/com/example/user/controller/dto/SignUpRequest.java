package com.example.user.controller.dto;

import com.example.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String email;

    private String password;

    private String nickname;

    public SignUpRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User toUser() {
        return new User(email, password, nickname);
    }
}

