package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Expiring;
import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.Repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;
    private ProductService productService;
    private PrepService prepService;

    private  WasteService wasteService;

    public StockService(ProductService productService, PrepService prepService, WasteService wasteService) {
        this.productService = productService;
        this.prepService = prepService;
        this.wasteService = wasteService;
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

                //cleaning expire products
                if(livePrepared.get(i).getExpireDate().isBefore(today)){
                    prepService.deletePrep(livePrepared.get(i).getId());
                }else{
                    Stock singlestock = new Stock();
                    Prep prep = livePrepared.get(i);
                    Product product = prep.getProduct();
                    singlestock.setProductid(product.getId());
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
            logger.error("Problem with prep database in stock service");
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
                    Product product = productService.getSingleProduct(prep.getId());

                    expiring.setCreated(prep.getProductionDate());
                    expiring.setExpire(prep.getExpireDate());

                    //calculate days
                    long differenceInDays = ChronoUnit.DAYS.between(expiring.getExpire(), today);
                    int daysleft = (int) differenceInDays;
                    expiring.setPrepid(prep.getId());
                    expiring.setName(product.getName());
                    expiring.setProductid(product.getId());
                    expirings.add(expiring);
                }

            }

        } catch(Exception e){
            logger.error("Problem with prep database in stock service");
        }

        // Sort the list based on the daysLeft field
        Collections.sort(expirings, Comparator.comparingInt(Expiring::getDaysLeft));

        return  expirings;
    }

    public void removePrepAsExpired(Long id){
        try {
            List<Prep> preps = prepService.findByIds(id);
            List<Product> products = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();
            for (int i =0 ;i < preps.size(); i++){
                products.add(preps.get(i).getProduct());
                quantity.add(preps.get(i).getAmount());
            }
            wasteService.saveWaste(products, quantity);
            prepService.deletePrep(id);
            logger.info("Product removed and added to Wastage");
        } catch (Exception e){
            logger.error("Product not removed properly");
        }

    }

}
