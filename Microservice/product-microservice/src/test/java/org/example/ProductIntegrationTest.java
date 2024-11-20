package org.example;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductIntegrationTest {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        mongoDBContainer.start();
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateAndRetrieveProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High-end laptop");
        product.setPrice(1000.00);
        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Laptop");
    }
}
