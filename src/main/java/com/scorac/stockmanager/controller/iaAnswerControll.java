package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.service.SetupService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.ChatClient;

import java.util.List;

@RestController
public class iaAnswerControll {

    private final ChatClient chatClient;

    private SetupService setupService;

    public iaAnswerControll(ChatClient chatClient, SetupService setupService) {
        this.chatClient = chatClient;
        this.setupService = setupService;
    }

    @GetMapping("/request")
    public String request(@RequestParam("selectedNamesSet") String[] selectedNamesSet) {
        //collecting restaurant informatin
        String restaurantInfo = setupService.restauranInfo();
        StringBuilder sb = new StringBuilder();
        String additonal="";
        for (int i = 0; i < selectedNamesSet.length; i++) {
            String str = selectedNamesSet[i];
            //last item (additional query)
            if (i == selectedNamesSet.length - 1) {
                additonal = str;
            } else {
                // Append other strings with a comma and space
                sb.append(str).append(", ");
            }
        }
        String productList = sb.toString();
        String question = "Based restaurant information \" "+ restaurantInfo +"\" and list of these products: " + productList +" create me 3 meal proposals. Additionally: "+ additonal +" . Send respond as Array ";
        // Respond from Neural network based on prompt
        return chatClient.call(question);
    }
}
