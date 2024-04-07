package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        List<Product> all = productRepository.findAll();
        // Sort the list alphabetically by the name attribute
        Collections.sort(all, Comparator.comparing(Product::getName));

        return all;
    }

    public String save(Product product) {
        List<Product> all = productRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getName().toLowerCase().equals(product.getName())) {
                return "Not added! This product already exist!";
            }
        }
        productRepository.save(product);
        return "Product added successfully!";
    }

    public List<String> productSearch(){
        List<String> productNames = new ArrayList<>();
        List<Product> all = productRepository.findAll();
        for (int i =0; i< all.size();i++){
            productNames.add(all.get(i).getName());
        }
        return productNames;
    }

    public List<Product> getAllIngredients() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByIds(List<Long> productIds) {

        return productRepository.findAllById(productIds);
    }
}
