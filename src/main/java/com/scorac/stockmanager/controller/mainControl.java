package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.*;
import com.scorac.stockmanager.model.Entity.Booking;
import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.Entity.Setup;
import com.scorac.stockmanager.service.*;
//import com.scorac.stockmanager.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    private BookingService bookingService;
    private  WasteService wasteService;


    public mainControl(StockService stockService, WeatherService weatherService,
                       ProductService productService, DayEventService dayEventService,
                       SetupService setupService, BookingService bookingService, WasteService wasteService) {
        this.stockService = stockService;
        this.weatherService = weatherService;
        this.productService = productService;
        this.dayEventService = dayEventService;
        this.setupService = setupService;
        this.bookingService = bookingService;
        this.wasteService = wasteService;
    }


    
    @GetMapping({"/","home","index"})
    public String homePage(Model model){
        LocalDate today = LocalDate.now();
        List<Expiring> expires = stockService.expireProducts();
        model.addAttribute("expires" , expires);
        return "index";
    }

    @GetMapping("/chatquery")
    public String queryChat(Model model){
        LocalDate today =LocalDate.now();
        List<Expiring> expires = stockService.expireProducts();
        model.addAttribute("expires" , expires);
        return "queryGPT";
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

    @PostMapping("/addWaste")
    private String addWaste(@ModelAttribute Prep prep , @RequestParam(value = "productIds") List<Long> productIds, @RequestParam(value = "quantities") List<Integer> quantities, RedirectAttributes redirectAttributes){

        List<Product> products = productService.getProductsByIds(productIds);
        //sorting products list (not nice but fast fix the problem)
        List<Product> sorted = new ArrayList<>();
        for (int i =0; i < productIds.size(); i++ ){
            for(int j =0; j< products.size();j++){
                if(productIds.get(i)== products.get(j).getId()){
                    sorted.add(products.get(j));
                }
            }
        }

        String respond = wasteService.saveWaste(sorted, quantities);

        if(respond.contains("Faild")){
            redirectAttributes.addFlashAttribute("error", respond);
        }else{
            redirectAttributes.addFlashAttribute("message", respond);
        }
        return "redirect:/wastage";
    }

    @PostMapping("/addBooking")
    public ModelAndView addBooking(@Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("errorPage");
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            return modelAndView;
        }
        bookingService.saveBooking(booking);
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/error")
    public String handleError() {
        // View to display as an error page.
        return "errorPage";
    }
    @GetMapping("/editday")
    public String editday(){
        return "bookingAdd";
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
