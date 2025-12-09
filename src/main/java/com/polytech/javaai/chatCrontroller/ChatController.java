package com.polytech.javaai.chatController;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    // Constructor
    public ChatController(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }



    @GetMapping("/ask")
    public Flux<String> ask(@RequestParam String message) {
        return chatClient
                .prompt()   // start prompt
                .user(message)  // provide user message
                .stream()     // execute call
                .content(); // get content
    }
}
