package com.example.learnjavaspringboot.product.services;

import com.example.learnjavaspringboot.Command;
import com.example.learnjavaspringboot.exceptions.ProductNotFoundException;
import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer input) {
        Optional<Product> optionalProduct = productRepository.findById(input);

        if (optionalProduct.isPresent()) {
            productRepository.deleteById(input);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ProductNotFoundException();
    }
}
