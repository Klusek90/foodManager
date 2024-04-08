package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Expiring;
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
import java.time.temporal.ChronoUnit;
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

    public StockService(StockRepository stockRepository, ProductService productService, PrepService prepService) {
        this.stockRepository = stockRepository;
        this.productService = productService;
        this.prepService = prepService;
    }

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

                    //calculate days
                    long differenceInDays = ChronoUnit.DAYS.between(today, singlestock.getExpireDate());
                    int days= (int) differenceInDays;
                    singlestock.setDaysLeft(days);

                    list.add(singlestock);
                }
            }
        }catch (Exception e){
            logger.severe("Problem with prep database in stock service");
        }

        return list;
    }

    public List<Expiring> expireProducts(){
        List<Expiring> expirings = new ArrayList<>();
        List<Prep> livePrepared = new ArrayList<>();
        LocalDate today = LocalDate.now();
        try{
            livePrepared = prepService.findAll();
            for(int i =0 ; i<livePrepared.size(); i++){

                Prep prep = livePrepared.get(i);
                LocalDate shortly  = today.plusDays(2);

                if (prep.getExpireDate().isBefore(shortly)){

                    Expiring expiring= new Expiring();
                    Product product = productService.getSingleProduct(prep.getId()).get();

                    expiring.setCreated(prep.getProductionDate());
                    expiring.setExpire(prep.getExpireDate());

                    //calculate days
                    long differenceInDays = ChronoUnit.DAYS.between(expiring.getExpire(), today);
                    int daysleft = (int) differenceInDays;
                    expiring.setName(product.getName());
                    expiring.setProductid(product.getId());
                    expirings.add(expiring);
                }

            }

        } catch(Exception e){
            logger.severe("Problem with prep database in stock service");
        }

        // Sort the list based on the daysLeft field
        Collections.sort(expirings, Comparator.comparingInt(Expiring::getDaysLeft));

        return  expirings;
    }

}
