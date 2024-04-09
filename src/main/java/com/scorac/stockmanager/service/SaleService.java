package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.Sale;
import com.scorac.stockmanager.service.Repository.RecipeProductRepository;
import com.scorac.stockmanager.service.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    private RecipeProductService recipeProductService;

    public SaleService(SaleRepository saleRepository, RecipeProductService recipeProductService) {
        this.saleRepository = saleRepository;
        this.recipeProductService = recipeProductService;
    }

    public Sale save(Sale sale){
        LocalDate timestamp = LocalDate.now();
        sale.setDate(timestamp);
        recipeProductService.updatestock(sale.getRecipeid(), sale.getQuantity());
        return saleRepository.save(sale);
    }


}
