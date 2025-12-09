package com.polytech.javaai.chatController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    // Constructor
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        return chatClient
                .prompt()   // start prompt
                .user(message)  // provide user message
                .call()     // execute call
                .content(); // get content
    }
}
