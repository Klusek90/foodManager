package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    private ProductRepository productRepository;


    public List<Product> listofALLSortedByName() {
        List<Product> all = stockRepository.findAll();
        // Sort the list alphabetically by the name attribute
        Collections.sort(all, Comparator.comparing(Product::getName));

        return all;
    }

//    public List<String> productSearch(){
//        List<String> productNames = new ArrayList<>();
//        List<Product> all = stockRepository.findAll();
//        for (int i =0; i< all.size();i++){
//            productNames.add(all.get(i).getName());
//        }
//        return productNames;
//    }
}
