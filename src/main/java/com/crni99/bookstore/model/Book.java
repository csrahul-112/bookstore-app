package com.crni99.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import org.hibernate.annotations.CreationTimestamp;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank(message = "{book.name.notBlank}")
	private String name;

	@Column(name = "price", nullable = false)
	@NotNull(message = "{book.price.notBlank}")
	private BigDecimal price;

	@Column(name = "authors", nullable = false)
	@NotBlank(message = "{book.authors.notBlank}")
	private String authors;

	@Column(name = "isbn", nullable = false)
	@NotBlank(message = "{book.isbn.notBlank}")
	@Pattern(regexp = "\\d{10}|\\d{13}", message = "{book.isbn.size}")
	private String isbn;

	@Column(name = "publisher", nullable = false)
	@NotBlank(message = "{book.publisher.notBlank}")
	private String publisher;

	@Column(name = "published_on", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{book.date.notNull}")
	private LocalDate publishedOn;

	@Column(name = "stock", nullable = false)
	private Integer stock;

	public boolean isAvailable() {
	    return stock != null && stock > 0;
	}
	@Column(name = "image_url")
    private String imageUrl;
	@Column(nullable = true)
	private Double rating; // e.g. from 0.0 to 5.0


	public Book() {
	}

	public Book(Long id, String name, BigDecimal price, String authors, String isbn, String publisher,
			LocalDate publishedOn) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.authors = authors;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publishedOn = publishedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(LocalDate publishedOn) {
		this.publishedOn = publishedOn;
	}

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;



	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", authors=" + authors + ", isbn=" + isbn
				+ ", publisher=" + publisher + ", publishedOn=" + publishedOn + "]";
	}

	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}


}
