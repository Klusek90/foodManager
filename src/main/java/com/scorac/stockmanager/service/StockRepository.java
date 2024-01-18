package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository  extends CrudRepository<Product, Long> {

    List<Product> findAll();
    Product findByName(String name);
    Product findOneById(Long id);
}
