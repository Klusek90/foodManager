package com.example.foodmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Control {

    @GetMapping({"/","home"})
    public String home(){
        return "homepage";
    }
}
