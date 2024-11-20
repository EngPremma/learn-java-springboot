package com.example.learnjavaspringboot;

import com.example.learnjavaspringboot.product.ProductRepository;
import com.example.learnjavaspringboot.product.model.Product;
import com.example.learnjavaspringboot.product.model.ProductDTO;
import com.example.learnjavaspringboot.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_products_exist_when_get_products_service_return_product_dto() {
//    Given
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product1");
        product1.setDescription("Product1 desc");
        product1.setPrice(11.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product2");
        product2.setDescription("Product2 desc");
        product2.setPrice(1.0);

        productList.add(product1);
        productList.add(product2);

        List<ProductDTO> productDTOS = productList.stream().map(ProductDTO::new).toList();

        when(productRepository.findAll()).thenReturn(productList);

//        When
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

//        Then
        assertEquals(ResponseEntity.ok(productDTOS), response);
    }
}
