package com.example.apiproject.service;

import com.example.apiproject.dto.AuthRequest;
import com.example.apiproject.entity.User;
import com.example.apiproject.repository.AccountRepository;
import com.example.apiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void update(User user) {
        user.setAccount(accountRepository.findByUser(user));
        userRepository.save(user);
    }
}
