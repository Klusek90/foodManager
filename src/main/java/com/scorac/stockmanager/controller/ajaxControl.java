package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Order;
import com.scorac.stockmanager.model.OrderLine;
import com.scorac.stockmanager.model.Weather;
import com.scorac.stockmanager.service.DayEventService;
//import com.scorac.stockmanager.service.StockService;
import com.scorac.stockmanager.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ajaxControl {

    @GetMapping("/greenboxbooking")
    public ResponseEntity<Map<String, String>> getTestValues() {
        Map<String, String> response = new HashMap<>();
        response.put("booking", "20");
        response.put("notes", "Here are some notes passed by chefs about preparation etc.");
        return ResponseEntity.ok(response);
    }

}
