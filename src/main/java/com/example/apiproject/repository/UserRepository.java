package com.example.apiproject.repository;

import com.example.apiproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByAccountUsername(String username);

    User findByUserId(Long userId);
    User findByEmail(String email);
}
