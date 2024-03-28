package com.scorac.stockmanager.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class iaAnswerControll {

    private final OpenAiChatClient chatClient;

    @Autowired
    public iaAnswerControll(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/request")
    public String request(){
        String requst = "tell me something about yourself";
        return chatClient.call(requst);
    }
}
