package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.*;
import com.scorac.stockmanager.service.*;
//import com.scorac.stockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
public class mainControl {
    @Autowired
    private StockService stockService;
    private WeatherService weatherService;
    private ProductService productService;
    private DayEventService dayEventService;
    private SetupService setupService;

    public mainControl(WeatherService weatherService, DayEventService dayEventService,
                       StockService stockService, ProductService productService,
                       SetupService setupService) {
        this.weatherService = weatherService;
        this.dayEventService = dayEventService;
        this.stockService = stockService;
        this.productService = productService;
        this.setupService = setupService;
    }

    @GetMapping("/error")
    public String handleError() {
        // View to display as an error page.
        return "errorPage";
    }
    
    @GetMapping({"/","home","index"})
    public String homePage(Model model){
        LocalDate today = LocalDate.now();
        List<Expiring> expires = stockService.expireProducts();
        //test
//        expires.add(new Expiring("name", 2L, 2, today, today));
        model.addAttribute("expires" , expires);
        Weather weather = weatherService.getWeather("Rugby"); // Example city
        model.addAttribute("weather", weather);

        return "index";
    }




    @GetMapping("/chatquery")
    public String queryChat(Model model){
        LocalDate today =LocalDate.now();
        List<Expiring> expires = stockService.expireProducts();
        model.addAttribute("expires" , expires);
        return "queryGPT";
    }



    @GetMapping("/reports")
    public String reports(){
        return "reports";
    }

    //
    @GetMapping("/getTestValues")
    public ResponseEntity<Map<String, String>> getTestValues() {
        Map<String, String> response = new HashMap<>();
        response.put("id", "costam");
        response.put("content", "costam2");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/setup")
    public String setup(Model model){
        Setup setup = setupService.getSetup();
        model.addAttribute("setup", setup);
        return "setup";
    }

    @PostMapping("/updateSetup")
    public String updateSetup(@ModelAttribute Setup setup, RedirectAttributes redirectAttributes) {
        setupService.updateSetup(setup);
        redirectAttributes.addFlashAttribute("message", "Setup Updated");
        return "redirect:/setup";
    }

    @GetMapping("/wastage")
    public String wastage(Model model){
        List<String> productNames= productService.productSearch();
        model.addAttribute("itemsName", productNames);
        return "wastage";
    }

    @GetMapping("/editday")
    public String editday(){
        return "editday";
    }


    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping ("/logout")
    public String logout(){
        return "login";
    }



}
