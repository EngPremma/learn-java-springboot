package com.example.learnjavaspringboot.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewUserController {
    private final PasswordEncoder encoder;

    private final CustomerUserRepository customerUserRepository;

    public CreateNewUserController(PasswordEncoder encoder, CustomerUserRepository customerUserRepository) {
        this.encoder = encoder;
        this.customerUserRepository = customerUserRepository;
    }

    @PostMapping("/create-new-user")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> optionalCustomUser = customerUserRepository.findById(user.getUsername());

        if (optionalCustomUser.isEmpty()) {
            customerUserRepository.save(new CustomUser(user.getUsername(), encoder.encode(user.getPassword())));

            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.badRequest().body("Failure");
    }
}
