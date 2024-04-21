package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Made;
import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.service.Repository.MadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MadeService {
    @Autowired
    private MadeRepository madeRepository;

    public void save(Prep prep){
        Made made = new Made();
        made.setCreated(prep.getProductionDate());
        made.setAmount(prep.getAmount());
        made.setProduct(prep.getProduct());
        madeRepository.save(made);
    }

    public List<Made> createdInDate(LocalDate date){
        return madeRepository.findAllByCreated(date);
    }
}
