package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.Entity.Waste;
import com.scorac.stockmanager.service.Repository.WasteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WasteService {

    @Autowired
    private WasteRepository wasteRepository;
    private PrepService prepService;
    private ProductService productService;

    public WasteService(PrepService prepService, ProductService productService) {
        this.prepService = prepService;
        this.productService = productService;
    }

    private static final Logger logger = LoggerFactory.getLogger(WasteService.class);


    public  String saveWaste(List<Product> products, List<Integer> quantities){
        try{
            LocalDate datestamp = LocalDate.now();
            for(int i =0 ; i< products.size(); i++){

                Waste waste= new Waste();
                waste.setDate(datestamp);
                waste.setProduct(products.get(i));
                waste.setQuantity(quantities.get(i));
                wasteRepository.save(waste);

                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(products.get(i).getName());
                productDTO.setQuantity(quantities.get(i));
                productDTO.setProductid(products.get(i).getId());
                prepService.updatestock(productDTO);
                logger.info("Wastage added");

            }
            return "Product waste succesfuly";

        }catch (Exception e){
            logger.error("Fail to add wastage");
            return "Faild to process";
        }

    }
}
