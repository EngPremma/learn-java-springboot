package com.example.learnjavaspringboot.product.services;

import com.example.learnjavaspringboot.Command;
import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import com.example.learnjavaspringboot.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        Product saveProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(saveProduct));
    }
}
