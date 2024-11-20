package com.example.learnjavaspringboot.product;

import com.example.learnjavaspringboot.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// For calling database
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Spring data JPA
    List<Product> findByNameContaining(String name);

    //    JPQL
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String name);
}
