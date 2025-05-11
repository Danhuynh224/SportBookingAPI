package com.example.apiproject.controller;

import com.example.apiproject.JWT.JwtUtil;
import com.example.apiproject.dto.AuthRequest;
import com.example.apiproject.dto.RegisterRequest;
import com.example.apiproject.entity.User;
import com.example.apiproject.service.AccountService;
import com.example.apiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private UserService userService;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = accountService.login(request);
        if (user != null) {
            String token = jwtUtil.generateToken(user.getAccount().getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        accountService.save(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }
}

