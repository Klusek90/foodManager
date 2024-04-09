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

        String question = "Based on these products: " + productList +" create me 3 meal proposals. Additionally: "+ additonal +" . Send respond as Array";

        // Wywołanie metody chatClient.call z zbudowanym łańcuchem znaków
        return chatClient.call(question);
    }
}
