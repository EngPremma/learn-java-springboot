package com.example.learnjavaspringboot.product.services;

import com.example.learnjavaspringboot.Query;
import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import com.example.learnjavaspringboot.product.model.ProductDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productsCache")
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream().map(ProductDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    //    Caching Scheduled
    @CacheEvict(value = "productsCache", allEntries = true)
    @Scheduled(fixedDelay = 7000, initialDelay = 0)
    public void evictProductsCache() {
        System.out.println("Evicting products cache");
    }
}
