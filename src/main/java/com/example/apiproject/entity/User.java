package com.example.apiproject.entity;

import com.example.apiproject.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 100)
    String fullName;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    @Column(nullable = false, unique = true, length = 15)
    String phone;

    @Column(nullable = false)
    String passwordHash;

    @Enumerated(EnumType.STRING)
    Role role;
}
