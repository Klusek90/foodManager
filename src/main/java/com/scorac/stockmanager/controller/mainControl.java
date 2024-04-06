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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class mainControl {
    @Autowired
    private final StockService stockService;
    private WeatherService weatherService;
    private ProductService productService;
    private DayEventService dayEventService;

    public mainControl(WeatherService weatherService, DayEventService dayEventService, StockService stockService, ProductService productService) {
        this.weatherService = weatherService;
        this.dayEventService = dayEventService;
        this.stockService = stockService;
        this.productService = productService;
    }

    // TODO: 10/02/2024 Add AI and ML algorithm  !!!
    // TODO: 12/02/2024 Add Pictures for product, users and recopies
    // TODO: 13/02/2024 proper render the card size
    // TODO: 14/02/2024 create report
    
    @GetMapping({"/","home","index"})
    public String homePage(Model model){
//        OrderLine line1 = new OrderLine(1, "Item 1", 2);
//        OrderLine line2 = new OrderLine(2, "Item 2", 3);
//        Order order1 = new Order(1, Arrays.asList(line1, line2));
//
//        OrderLine line3 = new OrderLine(3, "Item 3", 1);
//        OrderLine line4 = new OrderLine(4, "Item 4", 4);
//        Order order2 = new Order(2, Arrays.asList(line3, line4));

//        List<Order> orders = Arrays.asList(order1, order2);
//        model.addAttribute("orders", orders);

        Weather weather = weatherService.getWeather("Rugby"); // Example city
        model.addAttribute("weather", weather);

        model.addAttribute("testId", "test1");
        model.addAttribute("testContent", "test2");
        return "index";
    }

    @GetMapping("/recipes")
    public String recipes(Model model){
//        List<String> productNames= stockService.productSearch();
//        model.addAttribute("itemsName", productNames);
        return "recipes";
    }

    @GetMapping("/newrecipe")
    public String addRecipes(Model model){
        List<String> productList= productService.productSearch();
        model.addAttribute("itemsName", productList);
        return "recipesAdd";
    }

    @GetMapping("/newproduct")
    public String newProduct(Model model){
        List<String> productList= productService.productSearch();
        model.addAttribute("itemsName", productList);
        return "recipesNewProduct";
    }

    @GetMapping("/solution")
    public String queryChat(){
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
    public String setup(){
        return "setup";
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


    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        String respond =productService.save(product);
        redirectAttributes.addFlashAttribute("message", respond);
        return "redirect:/newproduct"; // Redirect to prevent duplicate submissions
    }
}
