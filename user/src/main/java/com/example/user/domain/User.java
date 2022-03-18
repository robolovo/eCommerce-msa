package com.example.user.domain;

import com.example.user.domain.vo.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String nickname;

    @Embedded
    private Money money;

    public User(Long id, String email, String password, String nickname, Money money) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.money = money;
    }

    public User(String email, String password, String nickname) {
        this(null, email, password, nickname, Money.init());
    }

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void subtractMoney(int totalPrice) {
        this.money.subtract(totalPrice);
    }
}
