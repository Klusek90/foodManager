package com.scorac.stockmanager.service;

import com.scorac.stockmanager.service.Repository.ProductQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductQuantityService {

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

}
