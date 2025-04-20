package com.example.apiproject.controller;


import com.example.apiproject.entity.Account;
import com.example.apiproject.entity.User;
import com.example.apiproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Account account) {
        if(account != null) {
            accountService.upDate(account);
            return ResponseEntity.ok("Cập nhật thành công");
        }
        return 	ResponseEntity.badRequest().body("Cập nhật thất bại");
    }

}
