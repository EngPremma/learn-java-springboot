package com.example.learnjavaspringboot.catfact;

import lombok.Data;

@Data
public class CatFactResponse {
    private String fact;

    private int length;
}
