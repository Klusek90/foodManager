package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PrepService {

    @Autowired
    private PrepRepository prepRepository;
     public List<Prep> findAll(){
            List<Prep> allprep = prepRepository.findAll();
         return allprep;
     }
     public void deletePrep(Long id){
         prepRepository.deleteById(id);
     }

     public void save(Prep prep){
        prepRepository.save(prep);
     }

     public void update(Prep prep){
         prepRepository.save(prep);
     }

     public Prep findPrepWithProductId(Long productId){
         List<Prep> currentPreps = prepRepository.findAllByProductid(productId);
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
}
