package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.DayEvent;
import com.scorac.stockmanager.service.DayEventRepository;
import com.scorac.stockmanager.service.DayEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/dayEvent")
public class dateControl {

    @Autowired
    private DayEventService dayEventService;
    @Autowired
    private DayEventRepository dayEventRepository;

    public dateControl(DayEventService dayEventService) {
        this.dayEventService = dayEventService;
    }

    @PostMapping("/add")
//    public ModelAndView addDayEvent(@RequestParam("event") int participants, @RequestParam("notes") String note) throws Exception {
    public String addDayEvent(@ModelAttribute DayEvent dayEvent){
        try{
            dayEventRepository.save(dayEvent);
            return "redirect:/calendar"; // Adjust the redirect as needed
        } catch (IllegalStateException e){
            return "userNew";
        }
    }
}
