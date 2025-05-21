package com.crni99.bookstore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")

public class ChatController {

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String userMessage = request.getMessage().toLowerCase();
        String botReply;

        if (userMessage.contains("hello") || userMessage.contains("hi")) {
            botReply = "Hello! How can I help you find a book today?";
        } else if (userMessage.contains("recommend") || userMessage.contains("suggest")) {
            botReply = "Sure! Are you interested in fiction, non-fiction, mystery, or sci-fi?";
        } else if (userMessage.contains("fiction")) {
            botReply = "Great choice! You might like 'The Great Gatsby' or 'To Kill a Mockingbird'.";
        } else if (userMessage.contains("mystery")) {
            botReply = "If you love mystery, try 'Gone Girl' or 'The Girl with the Dragon Tattoo'.";
        } else if (userMessage.contains("thanks") || userMessage.contains("thank you")) {
            botReply = "You're welcome! Feel free to ask more.";
        } else {
            botReply = "Sorry, I didn't understand that. Can you ask about book recommendations?";
        }

        return new ChatResponse(botReply);
    }

    // Request body class
    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Response class
    public static class ChatResponse {
        private String reply;

        public ChatResponse(String reply) {
            this.reply = reply;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}
