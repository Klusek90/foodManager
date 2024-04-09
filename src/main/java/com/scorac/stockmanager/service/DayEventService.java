package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.DayEvent;
import com.scorac.stockmanager.service.Repository.DayEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayEventService {

    @Autowired
    private DayEventRepository dayEventRepository;
    public void save(DayEvent dayEvent){
        dayEventRepository.save(dayEvent);
    }
    public List findAllData(){
        List <DayEvent> all = dayEventRepository.findAll();
        return  all;
    }
}
