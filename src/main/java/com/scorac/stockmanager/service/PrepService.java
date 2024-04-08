package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrepService {

    @Autowired
    private PrepRepository prepRepository;

// public List<Prep> findAll(){
//
//     return
// }
}
