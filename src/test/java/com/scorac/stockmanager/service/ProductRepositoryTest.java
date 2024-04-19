package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // Configure mock behavior if needed
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(1L, "cheese", 5, "dairy"),
                new Product(2L, "milk", 3, "dairy"),
                new Product(3L, "beef steak", 3, "meat")
        ));
    }
    @Test
    public void ProductRepository_Save_Product() {
        // Assert
        List<Product> list = productRepository.findAll();
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("milk", list.get(1).getName());
        Assertions.assertEquals("meat", list.get(2).getType());

    }
}

