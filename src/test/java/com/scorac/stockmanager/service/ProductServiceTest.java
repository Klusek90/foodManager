package com.scorac.stockmanager.service;

import com.scorac.stockmanager.controller.prepControl;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(1l,"milk",4,"diary"),
                new Product(2l,"chicken",4,"poultry"),
                new Product(2l,"basil",4,"herbs")
                ));
    }

    @Test
    public void testProductSearch() {
        // Execute the service method
        List<String> productNames = productService.productSearch();

        // Assertions to verify the correct behavior
        assertEquals(3, productNames.size());
        assertEquals("milk", productNames.get(0));
        assertEquals("chicken", productNames.get(1));
        assertEquals("basil", productNames.get(2));
    }
}