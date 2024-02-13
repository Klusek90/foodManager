package com.scorac.stockmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainControl {

//    @CrossOrigin
    @GetMapping({"/","home","index"})
    public String homePage(){
        return "index";
    }

    @GetMapping("/recipes")
    public String recipes(){
        return "recipes";
    }

    @GetMapping("/solution")
    public String queryChat(){
        return "queryGPT";
    }

    @GetMapping("/orders")
    public String orders(){
        return "orders";
    }

    @GetMapping("/reports")
    public String reports(){
        return "reports";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

    @GetMapping("/setup")
    public String setup(){
        return "setup";
    }

    @GetMapping("/wastage")
    public String wastage(Model model){
//        List<String> itemProducts = // fetch or create your list of item products
//        model.addAttribute("itemProducts", itemProducts);
        return "wastage";
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
