package com.example.apiproject.controller;

import com.example.apiproject.dto.AuthRequest;
import com.example.apiproject.dto.RegisterRequest;
import com.example.apiproject.entity.User;
import com.example.apiproject.service.AccountService;
import com.example.apiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
       User user = accountService.login(request);
       if(user != null) {
           return ResponseEntity.ok(user);
       }
       return 	ResponseEntity.badRequest().body("Đăng nhập thất bại");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        accountService.save(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }
}

