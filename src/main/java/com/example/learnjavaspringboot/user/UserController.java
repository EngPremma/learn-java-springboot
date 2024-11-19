package com.example.learnjavaspringboot.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Premma");
    }
}
