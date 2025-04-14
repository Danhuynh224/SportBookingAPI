package com.example.apiproject.entity;

import com.example.apiproject.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
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
    Long userId;

    @Column(nullable = true, length = 100)
    private String fullName = "";

    @Column(nullable = true, unique = true, length = 100)
    private String email = "";

    @Column(nullable = true, length = 15)
    private String phone = "";

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; // giả sử bạn có enum Role { ADMIN, USER }

    @Column(nullable = true, length = 100)
    private String sex = "Không xác định";

    @Column(nullable = true, length = 100)
    private String address = "";

    @Column(nullable = true)
    private LocalDate birthday = LocalDate.of(2000, 1, 1);

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;
}
