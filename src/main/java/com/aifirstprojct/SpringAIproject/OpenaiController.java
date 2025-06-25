package com.aifirstprojct.SpringAIproject;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenaiController {

    private final ChatClient chatClient;

    public OpenaiController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message) {
        String response = chatClient
                .prompt(message)
                .call()
                .content();

        // Return a proper ResponseEntity with combined text
        String finalResponse = "This is a dummy response to the question: " + message +
                "\nAI response is: " + response;

        return ResponseEntity.ok(finalResponse);
    }
}
