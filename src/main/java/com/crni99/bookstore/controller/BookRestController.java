package com.crni99.bookstore.controller;

import com.crni99.bookstore.model.Book;
import com.crni99.bookstore.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/books") // Base path for this controller
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves books, optionally filtered by a search term.
     * @param searchTerm optional search term to filter books by title, author, etc.
     * @return a list of books
     */
    @GetMapping
    public List<Book> getAllBooks(@RequestParam(value = "search", required = false) String searchTerm) {
        // Fetches the first page with a large size.
        // For production, proper pagination (page, size params) should be implemented.
        PageRequest pageRequest = PageRequest.of(0, 2000); // Default: page 0, size 2000
        return bookService.findPaginated(pageRequest, searchTerm).getContent();
    }

    /**
     * Retrieves a specific book by its ID.
     * @param id the ID of the book
     * @return the book if found, otherwise a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        return bookOptional.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}