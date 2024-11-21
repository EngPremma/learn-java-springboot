package com.example.learnjavaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LearnjavaspringbootApplication {

	public static void main(String[] args) {
		System.out.println("Spring boot");
		SpringApplication.run(LearnjavaspringbootApplication.class, args);
	}

}
