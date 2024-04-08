package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import com.scorac.stockmanager.service.Repository.StockRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StockService {

    private static final Logger logger = Logger.getLogger(StockService.class.getName());

    @Autowired
    private StockRepository stockRepository;
    private ProductService productService;
    private PrepService prepService;


    public List<Product> listofALLSortedByName() {
        List<Product> all = stockRepository.findAll();
        // Sort the list alphabetically by the name attribute
        Collections.sort(all, Comparator.comparing(Product::getName));

        return all;
    }

    public List<Stock> currentStock(){
        List<Stock> list = new ArrayList<>();
        List<Prep> livePrepared = new ArrayList<>();
        try {
           livePrepared = prepService.findAll();

            LocalDate today =  LocalDate.now();

            for(int i =0; i< livePrepared.size();i++){
                Stock singlestock = new Stock();
                //cleaning expire products
                if(livePrepared.get(i).getExpireDate().isBefore(today)){
                    prepService.deletePrep(livePrepared.get(i).getId());
                }else{
                    Prep prep = livePrepared.get(i);
                    Product product = productService.getSingleProduct(prep.getId()).get();
                    singlestock.setName(product.getName());
                    singlestock.setType(product.getType());
                    singlestock.setExpireDate(prep.getExpireDate());
                    singlestock.setProductiondate(prep.getProductionDate());
                    singlestock.setAmount(prep.getAmount());
                    int days= singlestock.getExpireDate().compareTo(today);
                    singlestock.setDaysLeft(days);

                    list.add(singlestock);
                }
            }
        }catch (Exception e){
            logger.severe("Problem with prep database in stock service");
        }

        return list;
    }

}
