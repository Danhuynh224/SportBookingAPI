package com.example.apiproject.service;


import com.example.apiproject.dto.AuthRequest;
import com.example.apiproject.dto.RegisterRequest;
import com.example.apiproject.entity.Account;
import com.example.apiproject.entity.User;
import com.example.apiproject.repository.AccountRepository;
import com.example.apiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User login(AuthRequest authRequest){
        Optional<Account> account = accountRepository.findByUsername(authRequest.getUsername());
        if(account.isPresent()){
            String encodedPassword = account.get().getPassword();
            String rawPassword = authRequest.getPassword();
            if(passwordEncoder.matches(rawPassword, encodedPassword))
            {
                return account.get().getUser();
            }
        }
        return null;
    }

    public Account save(RegisterRequest request) {
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        // giả định bạn đã tạo user object phù hợp:
        User user = new User();
        user.setEmail(request.getEmail());
        account.setUser(user);
        userRepository.save(account.getUser());
        return accountRepository.save(account);
    }
}

