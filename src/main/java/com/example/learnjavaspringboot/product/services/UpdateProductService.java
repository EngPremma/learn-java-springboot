package com.example.learnjavaspringboot.product.services;

import com.example.learnjavaspringboot.Command;
import com.example.learnjavaspringboot.exceptions.ProductNotFoundException;
import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import com.example.learnjavaspringboot.product.model.ProductDTO;
import com.example.learnjavaspringboot.product.model.UpdateProductCommand;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
//    Evict -> throw it away
//    @CacheEvict(value = "productsCache", key = "#command.getId()") // Update products cache data
//    Put -> throw it away then puts the return value of the method in the cache
    @CachePut(value = "productsCache", key = "#command.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());

        if (optionalProduct.isPresent()) {
            Product product = command.getProduct();
            product.setId(command.getId());
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
