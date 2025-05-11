package com.example.apiproject.controller;

import com.example.apiproject.dto.AuthRequest;
import com.example.apiproject.entity.User;
import com.example.apiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        if(user != null) {
            return ResponseEntity.ok("Cập nhật thành công");
        }
        return 	ResponseEntity.badRequest().body("Cập nhật thất bại");
    }

    @GetMapping("/getUserByMail")
    public ResponseEntity<?> getUserByMail(@RequestParam("mail") String mail) {
        User user = userService.getUserByMail(mail);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return 	ResponseEntity.badRequest().body("Không tìm thấy");
    }
}
