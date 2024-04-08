package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrepService {

    @Autowired
    private PrepRepository prepRepository;

    public PrepService(PrepRepository prepRepository) {
        this.prepRepository = prepRepository;
    }

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


}
