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

	@Query(value = "SELECT * FROM books WHERE name LIKE %:term%", nativeQuery = true)
	List<Book> findByNameContaining(@Param("term") String term);
	
	List<Book> findByCategory(Category category);
	
	List<Book> findByCategory_Id(Long categoryId);
	
	List<Book> findByStockGreaterThan(int stock);



}
