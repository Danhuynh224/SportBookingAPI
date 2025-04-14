package com.example.apiproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    @Id
    @Column(nullable = false, unique = true, length = 100)
    String username;
    @Column(nullable = false, length = 100)
    String password;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
