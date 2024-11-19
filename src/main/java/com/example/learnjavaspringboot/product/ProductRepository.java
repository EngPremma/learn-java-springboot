package com.example.learnjavaspringboot.product;

import com.example.learnjavaspringboot.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// For calling database
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
