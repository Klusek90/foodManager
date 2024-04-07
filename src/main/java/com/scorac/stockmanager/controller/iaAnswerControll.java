package com.scorac.stockmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.ChatClient;

@RestController
public class iaAnswerControll {


    private final ChatClient chatClient;

    public iaAnswerControll(ChatClient chatClient){
        this.chatClient = chatClient;
    };
    @GetMapping("/request")
    public String request(){
        String question = "tell me something about yourself";
        return chatClient.call(question);
    }
}
