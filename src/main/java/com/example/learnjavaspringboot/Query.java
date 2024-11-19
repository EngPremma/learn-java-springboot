package com.example.learnjavaspringboot;

import org.springframework.http.ResponseEntity;

// Using in get request
public interface Query<I, O> {
    ResponseEntity<O> execute(I input);
}
