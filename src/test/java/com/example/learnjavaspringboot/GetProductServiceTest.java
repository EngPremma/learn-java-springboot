package com.example.learnjavaspringboot;

import com.example.learnjavaspringboot.exceptions.ProductNotFoundException;
import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import com.example.learnjavaspringboot.product.model.ProductDTO;
import com.example.learnjavaspringboot.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class GetProductServiceTest {
    @Mock // Mock the response
    private ProductRepository productRepository;

    @InjectMocks // Test this service
    private GetProductService getProductService;

    @BeforeEach // Things we need before the test runs to set up properly
    public void setup() {
//        Initialize the repository & service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_product_dto() {

//        Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        //        When
        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        //        Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
//        Assert product repository was only call once
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception() {
//        Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

//        When & Then
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }

}

