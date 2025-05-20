--CATEGORIES
-- Insert categories first
INSERT INTO categories (id, name) VALUES (1, 'Fiction');
INSERT INTO categories (id, name) VALUES (2, 'Fantasy');
INSERT INTO categories (id, name) VALUES (3, 'Science');
INSERT INTO categories (id, name) VALUES (4, 'Biography');



-- BOOKS
INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (1, 'The Lord of the Rings', 99.99, 'J. R. R. Tolkien', '9780261103207', 'Allen & Unwin', '1954-07-29', 2, 10, 'https://covers.openlibrary.org/b/isbn/9780261103207-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (2, 'Harry Potter and the Half-Blood Prince', 55.00, 'J. K. Rowling', '9780439784542', 'Bloomsbury Publishing (UK)', '2005-07-16', 2, 8, 'https://covers.openlibrary.org/b/isbn/9780439784542-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (3, 'The Da Vinci Code', 250.89, 'Dan Brown', '0385504209', 'Doubleday', '2003-04-02', 1, 7, 'https://covers.openlibrary.org/b/isbn/0385504209-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (4, 'Fifty Shades of Grey', 26.95, 'E. L. James', '9781612130286', 'Vintage Books', '2012-04-17', 1, 5, 'https://covers.openlibrary.org/b/isbn/9781612130286-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (5, 'Twilight', 38.00, 'Stephenie Meyer', '0316160172', 'Little, Brown and Company', '2005-10-05', 2, 4, 'https://covers.openlibrary.org/b/isbn/0316160172-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (6, 'Hannibal', 66.60, 'Thomas Harris', '0385334877', 'Delacorte Press', '1999-06-08', 1, 3, 'https://covers.openlibrary.org/b/isbn/0385334877-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (7, 'The Hunger Games', 132.00, 'Suzanne Collins', '9780439023528', 'Scholastic Press', '2008-09-14', 2, 0, 'https://covers.openlibrary.org/b/isbn/9780439023528-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (8, 'Life of Pi', 26.95, 'Yann Martel', '0676973760', 'Knopf Canada', '2001-09-11', 1, 2, 'https://covers.openlibrary.org/b/isbn/0676973760-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (9, 'The Last Olympian', 150.68, 'Rick Riordan', '9781423101475', 'Disney Hyperion', '2009-05-05', 2, 6, 'https://covers.openlibrary.org/b/isbn/9781423101475-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (10, 'To Kill a Mockingbird', 12.99, 'Harper Lee', '9780061120084', 'J.B. Lippincott & Co.', '1960-07-11', 1, 10, 'https://covers.openlibrary.org/b/isbn/9780061120084-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (11, '1984', 9.99, 'George Orwell', '9780451524935', 'Secker & Warburg', '1949-06-08', 1, 8, 'https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (12, 'Harry Potter and the Philosopher''s Stone', 15.99, 'J.K. Rowling', '9780747532743', 'Bloomsbury', '1997-06-26', 2, 5, 'https://covers.openlibrary.org/b/isbn/9780747532743-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (13, 'Sapiens: A Brief History of Humankind', 18.50, 'Yuval Noah Harari', '9780062316097', 'Harvill Secker', '2011-01-01', 3, 12, 'https://covers.openlibrary.org/b/isbn/9780062316097-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (14, 'Becoming', 20.00, 'Michelle Obama', '9781524763138', 'Crown Publishing Group', '2018-11-13', 4, 7, 'https://covers.openlibrary.org/b/isbn/9781524763138-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (15, 'A Brief History of Time', 13.45, 'Stephen Hawking', '9780553380163', 'Bantam Dell Publishing Group', '1988-03-01', 3, 4, 'https://covers.openlibrary.org/b/isbn/9780553380163-L.jpg');

INSERT INTO books (id, name, price, authors, isbn, publisher, published_on, category_id, stock, image_url)
VALUES (16, 'The Lord of the Rings', 25.00, 'J.R.R. Tolkien', '9780261103573', 'George Allen & Unwin', '1954-07-29', 2, 0, 'https://covers.openlibrary.org/b/isbn/9780261103573-L.jpg');


-- USERS
INSERT INTO USERS (username, password, enabled) 
	VALUES ('admin', '{noop}admin', 1);
	

-- AUTHORITIES
INSERT INTO AUTHORITIES (username, authority) 
	VALUES ('admin', 'ADMIN');