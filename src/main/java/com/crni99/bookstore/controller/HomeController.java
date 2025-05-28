package com.crni99.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crni99.bookstore.model.Book;
import com.crni99.bookstore.service.BookService;

@Controller
public class HomeController {
	
	private static final int pageSizeDefault = 6;

	private final BookService bookService;

	public HomeController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(value = { "", "/" })
	public String listBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		return page(null, model, page, size);
	}

	@GetMapping("/search")
	public String searchBooks(@RequestParam("term") String term, Model model,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		if (term.isBlank()) {
			return "redirect:/";
		}
		return page(term, model, page, size);
	}
	@GetMapping("/new-arrivals")
	public String showNewArrivals(Model model) {
	    model.addAttribute("books", bookService.findNewArrivals());
	    model.addAttribute("title", "New Arrivals");
	    return "books-grid";
	}

	@GetMapping("/best-sellers")
	public String showBestSellers(Model model) {
	    model.addAttribute("books", bookService.findBestSellers());
	    model.addAttribute("title", "Best Sellers");
	    return "books-grid";
	}
	@GetMapping("/books")
	public String booksByCategory(@RequestParam("category") String categoryName, Model model) {
	    List<Book> books = bookService.findByCategoryName(categoryName);
	    model.addAttribute("books", books);
	    model.addAttribute("title", categoryName);
	    return "books-grid";
	}


	private String page(@RequestParam("term") String term, Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(pageSizeDefault);

		Page<Book> bookPage;

		if (term == null) {
			bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize), null);
		} else {
			bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize), term);
		}
		model.addAttribute("bookPage", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}	
		// ✅ Add dynamic sections to model
		model.addAttribute("recommendedBooks", bookService.getRecommendedBooks());
		model.addAttribute("newArrivals", bookService.findNewArrivals());
		model.addAttribute("bestSellers", bookService.findBestSellers());
		
		return "index";
	}
}