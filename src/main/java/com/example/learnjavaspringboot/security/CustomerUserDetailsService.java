package com.example.learnjavaspringboot.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final CustomerUserRepository customerUserRepository;

    public CustomerUserDetailsService(CustomerUserRepository customerUserRepository) {
        this.customerUserRepository = customerUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customerUserRepository.findById(username).get();

        return User
                .withUsername(customUser.getUsername())
                .password(customUser.getPassword())
//                .roles("admin", "basic")
//                .authorities("ADMIN")
                .build();
    }
}
