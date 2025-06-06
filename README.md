<div id="top">

<!-- HEADER STYLE: CLASSIC -->
<div align="center">

<img src="readmeai/assets/logos/purple.svg" width="30%" style="position: relative; top: 0; right: 0;" alt="Project Logo"/>

# BOOKSTORE-APP

<em></em>

<!-- BADGES -->
<!-- local repository, no metadata badges. -->

<em>Built with the tools and technologies:</em>

<img src="https://img.shields.io/badge/Spring-000000.svg?style=default&logo=Spring&logoColor=white" alt="Spring">
<img src="https://img.shields.io/badge/Docker-2496ED.svg?style=default&logo=Docker&logoColor=white" alt="Docker">
<img src="https://img.shields.io/badge/XML-005FAD.svg?style=default&logo=XML&logoColor=white" alt="XML">

</div>
<br>

---

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
    - [Project Index](#project-index)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
    - [Testing](#testing)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Overview



---

## Features

<code>❯ REPLACE-ME</code>

---

## Project Structure

```sh
└── bookstore-app/
    ├── Dockerfile
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    └── src
        ├── main
        └── test
```

### Project Index

<details open>
	<summary><b><code>BOOKSTORE-APP/</code></b></summary>
	<!-- __root__ Submodule -->
	<details>
		<summary><b>__root__</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ __root__</b></code>
			<table style='width: 100%; border-collapse: collapse;'>
			<thead>
				<tr style='background-color: #f8f9fa;'>
					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
					<th style='text-align: left; padding: 8px;'>Summary</th>
				</tr>
			</thead>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/Dockerfile'>Dockerfile</a></b></td>
					<td style='padding: 8px;'>- The Dockerfile configures a Docker image for deploying the bookstore application<br>- It leverages an OpenJDK 11 base image, exposes port 8080 for external access, and copies the application JAR file into the image<br>- Finally, it sets the entry point to execute the JAR, enabling containerized deployment of the bookstore application within the larger project.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/mvnw'>mvnw</a></b></td>
					<td style='padding: 8px;'>- The <code>mvnw</code> script bootstraps Maven projects<br>- It locates the projects base directory, optionally downloads the Maven wrapper JAR, and then executes the Maven wrapper, passing along any command-line arguments<br>- Environment variables configure Java and Maven locations, handling OS-specific paths<br>- The script ensures a consistent Maven execution environment across different operating systems.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/mvnw.cmd'>mvnw.cmd</a></b></td>
					<td style='padding: 8px;'>- Mvnw.cmd` bootstraps Maven builds<br>- It validates Java installation, locates the project directory, and optionally downloads the Maven wrapper JAR<br>- The script then executes the Maven wrapper, passing along command-line arguments<br>- Error handling and optional pre/post-execution scripts enhance its functionality within the broader project build process.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/pom.xml'>pom.xml</a></b></td>
					<td style='padding: 8px;'>- Pom.xml` configures the Book Store application, a Spring Boot project<br>- It defines project dependencies, including Spring Web, Data JPA, Thymeleaf templating, Spring Security, MySQL database connectivity, and email functionality<br>- The file manages build configurations using Maven, ensuring all necessary libraries are included for application deployment.</td>
				</tr>
			</table>
		</blockquote>
	</details>
	<!-- src Submodule -->
	<details>
		<summary><b>src</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ src</b></code>
			<!-- main Submodule -->
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.main</b></code>
					<!-- java Submodule -->
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.main.java</b></code>
							<!-- com Submodule -->
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<div class='directory-path' style='padding: 8px 0; color: #666;'>
										<code><b>⦿ src.main.java.com</b></code>
									<!-- crni99 Submodule -->
									<details>
										<summary><b>crni99</b></summary>
										<blockquote>
											<div class='directory-path' style='padding: 8px 0; color: #666;'>
												<code><b>⦿ src.main.java.com.crni99</b></code>
											<!-- bookstore Submodule -->
											<details>
												<summary><b>bookstore</b></summary>
												<blockquote>
													<div class='directory-path' style='padding: 8px 0; color: #666;'>
														<code><b>⦿ src.main.java.com.crni99.bookstore</b></code>
													<table style='width: 100%; border-collapse: collapse;'>
													<thead>
														<tr style='background-color: #f8f9fa;'>
															<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
															<th style='text-align: left; padding: 8px;'>Summary</th>
														</tr>
													</thead>
														<tr style='border-bottom: 1px solid #eee;'>
															<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\BookStoreApplication.java'>BookStoreApplication.java</a></b></td>
															<td style='padding: 8px;'>- BookStoreApplication serves as the main entry point for the Spring Boot application<br>- It initializes the Spring context, enabling the bookstore application to start and run<br>- This class bootstraps the entire application, allowing all configured components and services to function within the Spring framework<br>- The applications functionality is defined elsewhere in the project.</td>
														</tr>
													</table>
													<!-- controller Submodule -->
													<details>
														<summary><b>controller</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.crni99.bookstore.controller</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\BookController.java'>BookController.java</a></b></td>
																	<td style='padding: 8px;'>- BookController manages user interaction with books within a Spring-based bookstore application<br>- It handles displaying book lists, paginating results, searching, adding new books, saving updates, and deleting existing entries<br>- The controller interacts with a BookService to perform data operations and uses model attributes to render views, providing a user interface for book management.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\CartController.java'>CartController.java</a></b></td>
																	<td style='padding: 8px;'>- CartController<code> manages a users shopping cart within a Spring-based bookstore application<br>- It handles adding books to the cart, removing individual books or clearing the entire cart<br>- The controller interacts with </code>BookService<code> to retrieve book information and </code>ShoppingCartService` to manage cart persistence and updates, rendering cart contents and success messages to the user.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\CheckoutController.java'>CheckoutController.java</a></b></td>
																	<td style='padding: 8px;'>- CheckoutController manages the online checkout process within a Spring-based bookstore application<br>- It displays the shopping cart contents, collects customer billing information, and processes order placement<br>- Upon successful order completion, it interacts with billing and email services to confirm the purchase and clear the shopping cart, redirecting the user to the cart page with a confirmation message.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\HomeController.java'>HomeController.java</a></b></td>
																	<td style='padding: 8px;'>- HomeController manages the presentation layer for a bookstore application<br>- It handles user requests for displaying books, processing searches, and showcasing new arrivals and bestsellers<br>- The controller interacts with a BookService to retrieve and paginate book data, dynamically populating the view with relevant book lists and pagination controls<br>- It renders different views based on the request, including a main index page and category-specific pages.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\LoginController.java'>LoginController.java</a></b></td>
																	<td style='padding: 8px;'>- LoginController manages user authentication within the Bookstore application<br>- It presents a login page to unauthenticated users and redirects authenticated users to the book catalog<br>- The controller also handles access-denied scenarios, displaying an error page for unauthorized access attempts<br>- This ensures secure access to application resources based on user authentication status.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\controller\OrderController.java'>OrderController.java</a></b></td>
																	<td style='padding: 8px;'>- OrderController manages order-related web requests within a Spring-based bookstore application<br>- It handles displaying paginated order lists, searching orders by a given term, and showing details for a specific order<br>- The controller interacts with a BillingService to retrieve and present order data, including customer and book information, to the view.</td>
																</tr>
															</table>
														</blockquote>
													</details>
													<!-- model Submodule -->
													<details>
														<summary><b>model</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.crni99.bookstore.model</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\Book.java'>Book.java</a></b></td>
																	<td style='padding: 8px;'>- Book.java defines the Book entity for a bookstore application<br>- It represents a books attributes, including name, price, author, ISBN, publisher, publication date, stock, image URL, and category<br>- The entity uses JPA annotations for database persistence and includes validation constraints<br>- It also provides methods for accessing and modifying book details and determining availability.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\Category.java'>Category.java</a></b></td>
																	<td style='padding: 8px;'>- Category.java defines the Category entity for the Bookstore application<br>- It represents a category of books within the database, possessing a unique name and an auto-generated ID<br>- This model facilitates data persistence and management of book categories, serving as a crucial component of the applications data layer<br>- The class integrates with the applications persistence mechanism (likely JPA/Hibernate) to store and retrieve category information.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\Customer.java'>Customer.java</a></b></td>
																	<td style='padding: 8px;'>- The <code>Customer</code> class defines the data model for customer information within the Bookstore application<br>- It represents a customer entity, mapping attributes like name, address, and contact details to a database table named <code>customers</code><br>- The class uses annotations for database persistence and input validation, ensuring data integrity<br>- It facilitates customer management and order processing within the broader bookstore system.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\CustomerBooks.java'>CustomerBooks.java</a></b></td>
																	<td style='padding: 8px;'>- CustomerBooks represents a data structure within the bookstore applications model layer<br>- It encapsulates the relationship between a customer and their associated books<br>- The class facilitates associating a list of books with a specific customer, enabling efficient data management and retrieval within the applications broader functionality<br>- This supports core bookstore operations involving customer book records.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\GlobalModelAttributes.java'>GlobalModelAttributes.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\model\Order.java'>Order.java</a></b></td>
																	<td style='padding: 8px;'>- Order date, associated customer, and the book ordered<br>- This class facilitates database persistence through annotations, enabling the application to manage and track individual book orders<br>- Relationships with <code>Customer</code> and <code>Book</code> models are established for data integrity.</td>
																</tr>
															</table>
														</blockquote>
													</details>
													<!-- repository Submodule -->
													<details>
														<summary><b>repository</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.crni99.bookstore.repository</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\repository\BillingRepository.java'>BillingRepository.java</a></b></td>
																	<td style='padding: 8px;'>- BillingRepository provides data access for customer billing information within the Bookstore application<br>- It leverages Spring Datas CrudRepository to simplify database interactions, enabling crucial operations like customer retrieval and management<br>- This repository is a core component of the applications persistence layer, supporting the overall functionality of managing customer billing details.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\repository\BookRepository.java'>BookRepository.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\repository\CategoryRepository.java'>CategoryRepository.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\repository\OrderRepository.java'>OrderRepository.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
															</table>
														</blockquote>
													</details>
													<!-- security Submodule -->
													<details>
														<summary><b>security</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.crni99.bookstore.security</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\security\SecurityConfig.java'>SecurityConfig.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\security\SecurityWebApplicationInitializer.java'>SecurityWebApplicationInitializer.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
															</table>
														</blockquote>
													</details>
													<!-- service Submodule -->
													<details>
														<summary><b>service</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.crni99.bookstore.service</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\service\BillingService.java'>BillingService.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\service\BookService.java'>BookService.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\service\EmailService.java'>EmailService.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\java\com\crni99\bookstore\service\ShoppingCartService.java'>ShoppingCartService.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
															</table>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
					<!-- resources Submodule -->
					<details>
						<summary><b>resources</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.main.resources</b></code>
							<!-- templates Submodule -->
							<details>
								<summary><b>templates</b></summary>
								<blockquote>
									<div class='directory-path' style='padding: 8px 0; color: #666;'>
										<code><b>⦿ src.main.resources.templates</b></code>
									<table style='width: 100%; border-collapse: collapse;'>
									<thead>
										<tr style='background-color: #f8f9fa;'>
											<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
											<th style='text-align: left; padding: 8px;'>Summary</th>
										</tr>
									</thead>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\books-grid.html'>books-grid.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\cart.html'>cart.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\checkout.html'>checkout.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\error.html'>error.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\form.html'>form.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\index.html'>index.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\layout.html'>layout.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\list.html'>list.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\login.html'>login.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\order.html'>order.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\main\resources\templates\orders.html'>orders.html</a></b></td>
											<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
										</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
			<!-- test Submodule -->
			<details>
				<summary><b>test</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.test</b></code>
					<!-- java Submodule -->
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.test.java</b></code>
							<!-- com Submodule -->
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<div class='directory-path' style='padding: 8px 0; color: #666;'>
										<code><b>⦿ src.test.java.com</b></code>
									<!-- crni99 Submodule -->
									<details>
										<summary><b>crni99</b></summary>
										<blockquote>
											<div class='directory-path' style='padding: 8px 0; color: #666;'>
												<code><b>⦿ src.test.java.com.crni99</b></code>
											<!-- bookstore Submodule -->
											<details>
												<summary><b>bookstore</b></summary>
												<blockquote>
													<div class='directory-path' style='padding: 8px 0; color: #666;'>
														<code><b>⦿ src.test.java.com.crni99.bookstore</b></code>
													<table style='width: 100%; border-collapse: collapse;'>
													<thead>
														<tr style='background-color: #f8f9fa;'>
															<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
															<th style='text-align: left; padding: 8px;'>Summary</th>
														</tr>
													</thead>
														<tr style='border-bottom: 1px solid #eee;'>
															<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\BookStoreApplicationTests.java'>BookStoreApplicationTests.java</a></b></td>
															<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
														</tr>
													</table>
													<!-- controller Submodule -->
													<details>
														<summary><b>controller</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.test.java.com.crni99.bookstore.controller</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\BookControllerTest.java'>BookControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\CartControllerTest.java'>CartControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\CheckoutControllerTest.java'>CheckoutControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\HomeControllerTest.java'>HomeControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\LoginControllerTest.java'>LoginControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\controller\OrderControllerTest.java'>OrderControllerTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
															</table>
														</blockquote>
													</details>
													<!-- service Submodule -->
													<details>
														<summary><b>service</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.test.java.com.crni99.bookstore.service</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\service\BillingServiceTest.java'>BillingServiceTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\service\BookServiceTest.java'>BookServiceTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\service\EmailServiceTest.java'>EmailServiceTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='./bookstore-app/blob/master/src\test\java\com\crni99\bookstore\service\ShoppingCartServiceTest.java'>ShoppingCartServiceTest.java</a></b></td>
																	<td style='padding: 8px;'>Code>❯ REPLACE-ME</code></td>
																</tr>
															</table>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---

## Getting Started

### Prerequisites

This project requires the following dependencies:

- **Programming Language:** Java
- **Package Manager:** Maven
- **Container Runtime:** Docker

### Installation

Build bookstore-app from the source and intsall dependencies:

1. **Clone the repository:**

    ```sh
    ❯ git clone ../bookstore-app
    ```

2. **Navigate to the project directory:**

    ```sh
    ❯ cd bookstore-app
    ```

3. **Install the dependencies:**

<!-- SHIELDS BADGE CURRENTLY DISABLED -->
	<!-- [![docker][docker-shield]][docker-link] -->
	<!-- REFERENCE LINKS -->
	<!-- [docker-shield]: https://img.shields.io/badge/Docker-2CA5E0.svg?style={badge_style}&logo=docker&logoColor=white -->
	<!-- [docker-link]: https://www.docker.com/ -->

	**Using [docker](https://www.docker.com/):**

	```sh
	❯ docker build -t /bookstore-app .
	```
<!-- SHIELDS BADGE CURRENTLY DISABLED -->
	<!-- [![maven][maven-shield]][maven-link] -->
	<!-- REFERENCE LINKS -->
	<!-- [maven-shield]: https://img.shields.io/badge/Maven-C71A36.svg?style={badge_style}&logo=apache-maven&logoColor=white -->
	<!-- [maven-link]: https://maven.apache.org/ -->

	**Using [maven](https://maven.apache.org/):**

	```sh
	❯ mvn install
	```

### Usage

Run the project with:

**Using [docker](https://www.docker.com/):**
```sh
docker run -it {image_name}
```
**Using [maven](https://maven.apache.org/):**
```sh
mvn exec:java
```

### Testing

Bookstore-app uses the {__test_framework__} test framework. Run the test suite with:

**Using [maven](https://maven.apache.org/):**
```sh
mvn test
```

---

## Roadmap

- [X] **`Task 1`**: <strike>Implement feature one.</strike>
- [ ] **`Task 2`**: Implement feature two.
- [ ] **`Task 3`**: Implement feature three.

---

## Contributing

- **💬 [Join the Discussions](https://LOCAL//bookstore-app/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://LOCAL//bookstore-app/issues)**: Submit bugs found or log feature requests for the `bookstore-app` project.
- **💡 [Submit Pull Requests](https://LOCAL//bookstore-app/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your LOCAL account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone ./bookstore-app
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to LOCAL**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://LOCAL{//bookstore-app/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=/bookstore-app">
   </a>
</p>
</details>

---

## License

Bookstore-app is protected under the [LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

## Acknowledgments

- Credit `contributors`, `inspiration`, `references`, etc.

<div align="right">

[![][back-to-top]](#top)

</div>


[back-to-top]: https://img.shields.io/badge/-BACK_TO_TOP-151515?style=flat-square


---
