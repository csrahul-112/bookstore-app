package com.crni99.bookstore.controller;

import com.crni99.bookstore.model.Book;
import com.crni99.bookstore.model.ChatRequest;
import com.crni99.bookstore.model.ChatResponse;
import com.crni99.bookstore.service.BookService;
import com.crni99.bookstore.service.OllamaClient;
import com.crni99.bookstore.service.ShoppingCartService; // Added import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final OllamaClient ollamaClient;
    private final BookService bookService;
    private final ShoppingCartService shoppingCartService; // Added service

    public ChatController(OllamaClient ollamaClient, BookService bookService, ShoppingCartService shoppingCartService) { // Added to constructor
        this.ollamaClient = ollamaClient;
        this.bookService = bookService;
        this.shoppingCartService = shoppingCartService; // Initialize service
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String userMessage = request.getMessage();
        logger.info("Received chat message for Ollama: {}", userMessage);

        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ChatResponse("Message cannot be empty."));
        }

        try {
            String lowerUserMessage = userMessage.toLowerCase();

            // Intent: Check stock
            if (lowerUserMessage.contains("stock of") || lowerUserMessage.contains("is") && lowerUserMessage.contains("in stock") || lowerUserMessage.contains("available")) {
                String bookNameToSearch = extractBookName(lowerUserMessage, "stock of", "is", "in stock", "available");
                if (bookNameToSearch != null && !bookNameToSearch.isEmpty()) {
                    Page<Book> bookPage = bookService.findPaginated(PageRequest.of(0, 1), bookNameToSearch);
                    if (!bookPage.isEmpty()) {
                        Book foundBook = bookPage.getContent().get(0);
                        if (foundBook.isAvailable()) {
                            return ResponseEntity.ok(new ChatResponse("Yes, '" + foundBook.getName() + "' is currently in stock! We have " + foundBook.getStock() + " copies available."));
                        } else {
                            return ResponseEntity.ok(new ChatResponse("Sorry, '" + foundBook.getName() + "' is currently out of stock."));
                        }
                    } else {
                        return ResponseEntity.ok(new ChatResponse("I couldn't find a book titled '" + bookNameToSearch + "' in our catalog. Please check the spelling or try a different title."));
                    }
                }
            }

            // Intent: Add to cart
            if (lowerUserMessage.contains("add") && lowerUserMessage.contains("to cart")) {
                String bookNameToSearch = extractBookName(lowerUserMessage, "add", "to cart");
                 if (bookNameToSearch != null && !bookNameToSearch.isEmpty()) {
                    Page<Book> bookPage = bookService.findPaginated(PageRequest.of(0, 1), bookNameToSearch);
                    if (!bookPage.isEmpty()) {
                        Book foundBook = bookPage.getContent().get(0);
                        if (foundBook.isAvailable()) {
                            List<Book> cart = shoppingCartService.getCart();
                            // Check if already in cart to avoid duplicates, or handle quantity if needed
                            boolean alreadyInCart = cart.stream().anyMatch(b -> b.getId().equals(foundBook.getId()));
                            if (!alreadyInCart) {
                                cart.add(foundBook); // This modifies the list held by the session
                                return ResponseEntity.ok(new ChatResponse("Okay, I've added '" + foundBook.getName() + "' to your cart!"));
                            } else {
                                return ResponseEntity.ok(new ChatResponse("'" + foundBook.getName() + "' is already in your cart."));
                            }
                        } else {
                            return ResponseEntity.ok(new ChatResponse("Sorry, '" + foundBook.getName() + "' is out of stock and cannot be added to the cart."));
                        }
                    } else {
                        return ResponseEntity.ok(new ChatResponse("I couldn't find a book titled '" + bookNameToSearch + "' to add to your cart. Please check the spelling."));
                    }
                }
            }

            // Fallback to Ollama and general book search
            String systemPrompt = "You are BookGenie, a knowledgeable and enthusiastic bookstore assistant. Your personality is friendly, helpful, and passionate about literature.\n\n" +
                "GUIDELINES:\n" +
                "1. ALWAYS focus on book-related topics. If asked about non-book topics, gently redirect to books.\n" +
                "2. When recommending books, be specific with titles and authors when possible.\n" +
                "3. For book searches, encourage users to use the search feature with specific keywords.\n" +
                "4. Respond in a conversational, engaging tone that shows enthusiasm for books.\n" +
                "5. Keep responses concise (2-3 paragraphs maximum) but informative.\n" +
                "6. If you don't know a specific book, be honest but suggest similar alternatives.\n" +
                "7. Mention book genres, popular titles, or reading recommendations when relevant.\n" +
                "8. If asked about book availability or prices (unless it's a direct stock check as handled above), suggest using the search function or browsing the catalog.\n\n" +
                "Remember: You represent a bookstore that values literature and reading culture. Make customers feel welcome and excited about books!";
            
            String fullPrompt = systemPrompt + "\n\nUser: " + userMessage + "\nAssistant:";
            String ollamaReply = ollamaClient.generateResponse(fullPrompt);

            String bookInfo = checkForBookKeywordsAndFetch(userMessage);
            String finalReply = ollamaReply;

            if (!bookInfo.isEmpty() && !finalReply.toLowerCase().contains(bookInfo.toLowerCase().substring(0, Math.min(bookInfo.length(), 20) ))) { // Avoid duplicating book info if Ollama already included it
                finalReply += "\n\n" + bookInfo;
            }
            
            if (finalReply.startsWith(fullPrompt)) {
                finalReply = finalReply.substring(fullPrompt.length()).trim();
            } else if (finalReply.startsWith("Assistant:")) {
                 finalReply = finalReply.substring("Assistant:".length()).trim();
            }

            logger.info("Sending reply (Ollama/BookInfo): {}", finalReply);
            return ResponseEntity.ok(new ChatResponse(finalReply.trim()));

        } catch (Exception e) {
            logger.error("Exception during Ollama processing: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ChatResponse("Sorry, an unexpected error occurred while processing your request with the local AI model."));
        }
    }

    private String extractBookName(String message, String... keywordsToRemove) {
        String tempMessage = message.toLowerCase();
        for (String keyword : keywordsToRemove) {
            tempMessage = tempMessage.replace(keyword, "");
        }
        // Remove common articles and prepositions that might be left over
        tempMessage = tempMessage.replaceAll("\\b(is|a|an|the|for|of|to)\\b", "").trim();
        // A very basic attempt to clean up, might need more sophisticated parsing
        // For instance, if the message was "add The Great Gatsby to cart", it becomes "great gatsby cart"
        // Further refinement might be needed to isolate just "the great gatsby"
        // This is a placeholder for more robust NLP/NER if available
        return tempMessage.replaceAll("[^a-zA-Z0-9\\s']", "").trim(); // Keep alphanumeric, spaces, apostrophes
    }
    
    private String checkForBookKeywordsAndFetch(String message) {
        String lowerMessage = message.toLowerCase();
        StringBuilder bookDetails = new StringBuilder();

        // Enhanced keyword patterns for more accurate book search
        // Categories of keywords for better organization
        String[] bookRequestPatterns = {
            "book about", "books on", "book on", "books about", 
            "recommend books", "recommend a book", "book recommendation",
            "looking for books", "searching for books", "find books", "find a book",
            "books related to", "book related to", "books like", "book similar to"
        };
        
        String[] genreKeywords = {
            "fiction", "non-fiction", "mystery", "thriller", "romance", "fantasy", 
            "sci-fi", "science fiction", "historical", "biography", "autobiography",
            "self-help", "business", "philosophy", "poetry", "drama", "horror",
            "young adult", "children", "cookbook", "travel", "adventure"
        };
        
        String[] topicKeywords = {
            "java", "python", "programming", "coding", "software", "computer science",
            "history", "science", "mathematics", "physics", "chemistry", "biology",
            "psychology", "philosophy", "economics", "politics", "art", "music",
            "literature", "language", "health", "fitness", "nutrition", "meditation",
            "leadership", "management", "marketing", "finance", "investing"
        };
        
        // First check for book request patterns which are stronger indicators
        String searchTerm = null;
        for (String pattern : bookRequestPatterns) {
            if (lowerMessage.contains(pattern)) {
                int patternIndex = lowerMessage.indexOf(pattern);
                String afterPattern = message.substring(patternIndex + pattern.length()).trim();
                
                // Extract meaningful terms after the pattern
                if (!afterPattern.isEmpty()) {
                    // Take up to 4 words for more context
                    String[] words = afterPattern.split("\\s+");
                    StringBuilder termBuilder = new StringBuilder();
                    
                    // Get up to 4 meaningful words, skipping common words like "a", "the", etc.
                    String[] stopWords = {"a", "an", "the", "and", "or", "but", "if", "then", "so", "because"};
                    int wordCount = 0;
                    
                    for (int i = 0; i < words.length && wordCount < 4; i++) {
                        String word = words[i].toLowerCase();
                        boolean isStopWord = false;
                        
                        for (String stopWord : stopWords) {
                            if (word.equals(stopWord)) {
                                isStopWord = true;
                                break;
                            }
                        }
                        
                        if (!isStopWord && word.length() > 1) {
                            if (termBuilder.length() > 0) {
                                termBuilder.append(" ");
                            }
                            termBuilder.append(words[i]);
                            wordCount++;
                        }
                    }
                    
                    searchTerm = termBuilder.toString();
                    break;
                }
            }
        }
        
        // If no specific request pattern found, check for genre or topic keywords
        if (searchTerm == null) {
            // Check for genre keywords
            for (String genre : genreKeywords) {
                if (lowerMessage.contains(genre)) {
                    searchTerm = genre;
                    break;
                }
            }
            
            // If still no match, check topic keywords
            if (searchTerm == null) {
                for (String topic : topicKeywords) {
                    if (lowerMessage.contains(topic)) {
                        searchTerm = topic;
                        break;
                    }
                }
            }
        }

        // If we found a search term, fetch books
        if (searchTerm != null && !searchTerm.isEmpty()) {
            logger.info("Detected term for book search: {}", searchTerm);
            // Fetch a small number of books, e.g., top 3
            List<Book> books = bookService.findPaginated(PageRequest.of(0, 3), searchTerm).getContent(); 
            
            if (!books.isEmpty()) {
                bookDetails.append("📚 I found these books in our catalog that might interest you:\n\n");
                
                for (Book book : books) {
                    bookDetails.append("📖 **").append(book.getName()).append("**\n");
                    bookDetails.append("   Author: ").append(book.getAuthors()).append("\n");
                    bookDetails.append("   Price: $").append(book.getPrice()).append("\n\n");
                }
                
                bookDetails.append("Would you like more information about any of these books? Or should I search for something else?");
            } else {
                // Provide a helpful message when no books are found
                bookDetails.append("I searched our catalog for \"").append(searchTerm)
                          .append("\" but didn't find exact matches. Would you like me to suggest some popular books instead? Or you can try our search bar with different keywords.");
            }
        }
        
        return bookDetails.toString();
    }
}
