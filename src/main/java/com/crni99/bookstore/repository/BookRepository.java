package com.crni99.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crni99.bookstore.model.Book;
import com.crni99.bookstore.model.Category;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


	@Query("SELECT b FROM Book b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(b.authors) LIKE LOWER(CONCAT('%', :term, '%'))")
	List<Book> searchBooks(@Param("term") String term);

	
	List<Book> findByCategory(Category category);
	
	List<Book> findByCategory_Id(Long categoryId);
	
	List<Book> findByStockGreaterThan(int stock);
	
	@Query(value = "SELECT * FROM books ORDER BY rating DESC LIMIT 6", nativeQuery = true)
	List<Book> findTopRatedBooks();

	@Query(value = "SELECT * FROM books ORDER BY created_at DESC LIMIT 8", nativeQuery = true)
	List<Book> findNewArrivals();

	List<Book> findTop6ByOrderByRatingDesc(); // You must have a `rating` field in Book




}
