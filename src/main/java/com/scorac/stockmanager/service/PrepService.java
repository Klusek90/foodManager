package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.Entity.Waste;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrepService {

    @Autowired
    private PrepRepository prepRepository;

    private static final Logger logger = LoggerFactory.getLogger(PrepService.class);



    public List<Prep> findAll(){
            List<Prep> allprep = prepRepository.findAll();
         return allprep;
     }

     public List<Prep> findByIds(Long id){
       List<Prep> prep=  prepRepository.findAllById(id);
        return prep;
     }

     public void deletePrep(Long id){
         prepRepository.deleteById(id);

     }

     public void save(Prep prep){

        prepRepository.save(prep);
     }

     public void updateStock(Prep prep){
         prepRepository.save(prep);
     }



    public Prep findPrepWithProduct(Long productid){
         List<Prep> currentPreps = prepRepository.findByProduct_Id(productid);
         Prep prep =  findPrepWithSmallestDate(currentPreps);
         return prep;
     }


    public Prep findPrepWithSmallestDate(List<Prep> preps) {
         Prep comaprePrep= new Prep();

        // Retrieve the first element if the list is not empty
        if (!preps.isEmpty()) {
            comaprePrep =preps.get(0);
            for (int i =0; i < preps.size();i++){
                if(comaprePrep.getExpireDate().isAfter(preps.get(i).getExpireDate())){
                    comaprePrep = preps.get(i);
                }
            }
        }
        return comaprePrep;
    }

    public void updatestock(ProductDTO product){

            Prep prep = findPrepWithProduct(product.getProductid());

            if(prep.getAmount() >0) {
                int updateAmount = prep.getAmount() - product.getQuantity();
                if (updateAmount == 0) {
                    deletePrep(prep.getId());
                } else if( updateAmount < 0) {
                    deletePrep(prep.getId());
                    //convert from - to +
                    updateAmount =Math.abs(updateAmount);
                    prep = findPrepWithProduct(product.getProductid());
                    updateAmount = prep.getAmount()- updateAmount;

                    if(updateAmount > 0){
                        prep.setAmount(updateAmount);
                        updateStock(prep);
                    }else {
                        deletePrep(prep.getId());
                    }

                }else{
                    prep.setAmount(updateAmount);
                    updateStock(prep);
                }
            }
        }

}
