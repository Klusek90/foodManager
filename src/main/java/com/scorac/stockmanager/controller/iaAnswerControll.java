package com.scorac.stockmanager.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.ChatClient;

import java.util.List;

@RestController
public class iaAnswerControll {


    private final ChatClient chatClient;

    public iaAnswerControll(ChatClient chatClient){
        this.chatClient = chatClient;
    };
    @GetMapping("/request")
    public String request(@RequestParam("selectedNamesSet") String[] selectedNamesSet) {
        System.out.println(selectedNamesSet[0]);
        // Tworzenie łańcucha znaków z danymi wejściowymi
//        String productList = String.join(" ", products);
//        String veganStr = vegan ? "vegan" : "non-vegan";
//        String question = "Based on these products: " + productList + ", create me 3 " + veganStr + " meal proposals. Additionally: " + additionalPrompt;
        String question = "Based on these products: " + ", create me 3 " +  " meal proposals. Additionally:  . Send respond as Array";

        // Wywołanie metody chatClient.call z zbudowanym łańcuchem znaków
        return chatClient.call(question);
    }
}
