# BookStore Project Documentation (study.md)

## 1. Project Overview

This project is a Spring Boot-based online bookstore application. It features a comprehensive backend for managing books, customer orders, and inventory, integrated with a MySQL database. A key feature is an AI-powered chatbot, "BookGenie," built using Ollama (LLaMA 3 model) and integrated via JavaScript on the frontend and a Spring Boot controller on the backend. The chatbot assists users with book recommendations, stock queries, and adding books to their shopping cart.

## 2. Features

### Frontend:
-   Browse books by category, new arrivals, best sellers.
-   Search functionality for books (by title, author, ISBN).
-   Detailed book view.
-   Shopping cart functionality.
-   User login and order history (basic structure).
-   Integrated AI Chatbot ("BookGenie") for user assistance:
    -   General book-related queries and recommendations.
    -   Check book stock.
    -   Add books to the shopping cart.
    -   Collapsible UI widget.

### Backend (Spring Boot):
-   RESTful APIs for book management (CRUD operations - though primarily focused on read and search for the chatbot).
-   API endpoint for chatbot interaction (`/api/chat`):
    -   Processes user messages.
    -   Handles specific intents (stock check, add to cart) by interacting with internal services.
    -   Communicates with Ollama service for general AI responses.
-   Shopping cart management (session-based).
-   Order processing (basic structure).
-   Integration with `OllamaClient` to connect to the Ollama LLaMA 3 model.
-   Service layer for business logic (BookService, ShoppingCartService, OllamaClient, etc.).
-   Repository layer for database interaction (Spring Data JPA).

### Database (MySQL):
-   Stores book information (title, author, price, ISBN, publisher, stock, category, etc.).
-   Stores customer information and orders (structure exists).
-   Stores categories for books.

### Chatbot (BookGenie):
-   Frontend UI implemented with HTML, CSS, and JavaScript.
-   Backend Spring Boot controller (`ChatController`) to handle chat requests.
-   Integration with Ollama (LLaMA 3 model) for natural language understanding and response generation.
-   Custom prompts and guidelines for the AI model to act as a bookstore assistant.
-   Functionality to:
    -   Provide book recommendations.
    -   Check book availability (stock) by querying the database.
    -   Add books to the user's shopping cart by interacting with the backend services.

## 3. Tech Stack

-   **Backend:** Spring Boot (Java)
-   **Database:** MySQL
-   **AI Model Integration:** Ollama (LLaMA 3 model)
-   **Frontend:** HTML, CSS, JavaScript, Thymeleaf (for server-side templating)
-   **Build Tool:** Apache Maven
-   **API Communication:** REST APIs, HTTP
-   **Other Libraries:** Spring Data JPA, Spring Web, SLF4J (logging), Jackson (JSON processing), RestTemplate.

## 4. Explanation of Key Components

### Book Management:
-   **Model:** `Book.java` defines the structure of a book entity, including fields like `name`, `price`, `authors`, `isbn`, `stock`, and `category`.
-   **Repository:** `BookRepository.java` (extends `JpaRepository`) handles database operations for books.
-   **Service:** `BookService.java` contains business logic related to books, such as finding books by various criteria (`findPaginated`, `findByCategoryName`, `findBookById`). The `findPaginated` method is used by the chatbot to search for books by term. The `isAvailable()` method in the `Book` model (derived from the `stock` field) is used for stock checks.

### Cart System:
-   **Service:** `ShoppingCartService.java` manages the shopping cart. It is session-based, meaning the cart contents are stored in the user's HTTP session.
    -   `getCart()`: Retrieves the current list of books in the cart from the session.
    -   Adding to cart (via Chatbot): The `ChatController` retrieves the cart using `shoppingCartService.getCart()`, then directly adds the `Book` object to this list if the book is found and in stock.
-   **Controller:** `CartController.java` handles web requests related to the shopping cart page (e.g., displaying cart, removing items through the UI).

### Chatbot API Structure:
-   **Endpoint:** `/api/chat` (POST request) handled by `ChatController.java`.
-   **Request:** `ChatRequest.java` (JSON object with a `message` field).
-   **Response:** `ChatResponse.java` (JSON object with a `reply` field).
-   **Ollama Integration:** `OllamaClient.java` is a service responsible for making HTTP POST requests to the Ollama API endpoint (`http://localhost:11434/api/generate` by default) with the specified model (`llama3` by default) and user prompt. It uses `RestTemplate` for this.

### How Chatbot Integrates with Backend:

1.  **User Interaction (Frontend):**
    -   The user types a message in the chatbot UI (`chatbot.js`).
    -   JavaScript sends an asynchronous POST request to `/api/chat` with the user's message.

2.  **Backend Processing (`ChatController`):**
    -   The `chat()` method in `ChatController` receives the `ChatRequest`.
    -   **Intent Recognition (Prioritized):**
        -   It first checks for specific actionable intents like "check stock" or "add to cart" using keyword matching (e.g., "stock of", "is ... in stock", "add ... to cart").
        -   A helper method `extractBookName()` attempts to parse the book title from the user's message.
    -   **Action Execution (if intent matched):**
        -   **Stock Check:** If a stock check intent is detected, it uses `bookService.findPaginated()` to find the book. If found, it checks `book.isAvailable()` and `book.getStock()` and forms a direct response.
        -   **Add to Cart:** If an add-to-cart intent is detected, it finds the book similarly. If the book is available, it retrieves the current cart from `shoppingCartService.getCart()` and adds the `Book` object to the list. A confirmation message is then sent.
    -   **Fallback to Ollama (if no specific intent matched):**
        -   If no actionable intent is recognized, a detailed system prompt (defining BookGenie's persona and guidelines) is combined with the user's message.
        -   This full prompt is sent to the Ollama API via `ollamaClient.generateResponse()`.
        -   The `ChatController` may also call `checkForBookKeywordsAndFetch()` which uses `bookService.findPaginated()` to find relevant books based on keywords in the user's message. This information can be appended to Ollama's response if not already covered.
    -   **Response Generation:** The controller constructs a `ChatResponse` and sends it back to the frontend.

3.  **Display Response (Frontend):**
    -   `chatbot.js` receives the JSON response and displays the `reply` in the chat messages area.

## 5. Why Ollama?

Ollama was chosen for this project primarily for its ability to run large language models (LLMs) locally. This offers several advantages over cloud-based APIs like OpenAI:

-   **Cost-Effectiveness:** Running models locally with Ollama eliminates API call costs. While there's an initial setup and potential hardware consideration, ongoing operational expenses for the AI component are minimal, which is ideal for development, experimentation, and projects with budget constraints.
-   **Privacy and Data Control:** Since the model runs on your own machine (or a server you control), user data sent to the chatbot does not leave your environment. This is a significant advantage for applications handling sensitive information or for users concerned about data privacy.
-   **Offline Capability:** Once Ollama and the desired models are set up, the chatbot can function without an active internet connection to an external API provider. This is beneficial for local development or specific deployment scenarios.
-   **Customization and Flexibility:** Ollama allows for easy switching between different open-source models. Users can experiment with various LLMs to find the best fit for their needs in terms of performance, size, and capabilities.
-   **Ease of Setup for Local Development:** Ollama simplifies the process of downloading and running various open-source LLMs, making it accessible for developers to integrate AI capabilities quickly.

### Comparison: Ollama vs. OpenAI

| Feature         | Ollama (Self-Hosted LLMs)                     | OpenAI (Cloud API - e.g., GPT-3.5/4)        |
|-----------------|-----------------------------------------------|---------------------------------------------|
| **Cost**        | - No per-call API fees. <br> - Potential hardware costs for running larger models. | - Pay-as-you-go based on token usage. <br> - Can become expensive for high-volume applications. |
| **Privacy**     | - High. Data remains in your local environment. | - Lower. Data is sent to OpenAI servers for processing (though policies exist for data handling). |
| **Setup**       | - Requires installing Ollama and downloading models locally. <br> - Configuration of local API endpoint. | - Simpler initial setup (API key integration). <br> - No local model management needed. |
| **Performance** | - Dependent on local hardware capabilities. <br> - Response times can vary. | - Generally fast and optimized performance from cloud infrastructure. |
| **Model Access**| - Access to a wide range of open-source models. <br> - User manages model updates. | - Access to state-of-the-art proprietary models. <br> - Models updated and maintained by OpenAI. |
| **Scalability** | - Scaling requires managing local infrastructure. | - Highly scalable through cloud infrastructure. |
| **Offline Use** | - Yes, once models are downloaded.             | - No, requires internet connectivity.        |

While OpenAI offers powerful, state-of-the-art models with easy API access, Ollama provides a compelling alternative for projects prioritizing cost, privacy, and local control, especially during development and for smaller-scale deployments.

## 6. Setup Instructions

1.  **Prerequisites:**
    -   Java JDK (11 or higher recommended)
    -   Apache Maven
    -   MySQL Server (running and accessible)
    -   Ollama installed and running with the `llama3` model (or the model specified in `application.properties`).
        -   Pull the model: `ollama pull llama3`
        -   Ensure Ollama is serving on `http://localhost:11434` (or update `ollama.api.url` in `application.properties`).

2.  **Database Setup:**
    -   Create a MySQL database (e.g., `bookstore1` as per default `application.properties`).
    -   Update `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in `bookstoreSinha/src/main/resources/application.properties` to match your MySQL setup.
    -   The application uses `spring.jpa.hibernate.ddl-auto=update`, so Hibernate will attempt to create/update tables based on entities. For a fresh setup, it should create the necessary tables.

3.  **Application Configuration:**
    -   Review `bookstoreSinha/src/main/resources/application.properties` for other settings (email, Ollama URL/model if different from default).

4.  **Build the Project:**
    -   Navigate to the `bookstoreSinha` project root directory in your terminal.
    -   Run: `mvn clean install`

5.  **Run the Application:**
    -   After a successful build, run: `mvn spring-boot:run`
    -   Alternatively, you can run the packaged JAR file: `java -jar target/bookstore-0.0.1-SNAPSHOT.jar` (the JAR name might vary).

6.  **Access the Application:**
    -   Open your web browser and go to `http://localhost:8080` (or the configured port).

## 7. Deployment Options

Hosting this application involves deploying the Spring Boot backend, the MySQL database, and the Ollama service with its models. Here are some common deployment options:

### A. Platform-as-a-Service (PaaS)

PaaS platforms simplify deployment by managing the underlying infrastructure.

1.  **Render:**
    -   **Explanation:** Render supports deploying Spring Boot applications (as web services) and managed PostgreSQL/MySQL databases. You can also deploy Docker containers, which would be necessary for Ollama.
    -   **Spring Boot JAR:** Deploy as a Web Service. Render can often detect Java applications and build them using a `render.yaml` configuration or directly from a Git repository.
    -   **Ollama:** Deploy Ollama as a Docker container (using a pre-built Ollama image or your custom Dockerfile) on a separate Render service. Ensure network connectivity between the Spring Boot app and the Ollama service.
    -   **Database:** Use Render's managed MySQL or connect to an external one.

2.  **Railway:**
    -   **Explanation:** Railway offers a simple deployment experience using Nixpacks or Dockerfiles. It provides managed databases.
    -   **Spring Boot JAR:** Deploy using a Dockerfile or let Nixpacks build it.
    -   **Ollama:** Deploy Ollama via a Dockerfile on a separate service. Configure networking.
    -   **Database:** Use Railway's managed database plugins.

3.  **Heroku (with caveats for Ollama):**
    -   **Explanation:** Heroku is a popular PaaS. Deploying Spring Boot is straightforward. However, running Ollama directly on Heroku dynos can be challenging due to resource limitations (CPU, RAM, disk for models) and the ephemeral nature of dynos, unless using more expensive performance dynos and persistent storage solutions.
    -   **Spring Boot JAR:** Deploy using Heroku's Java buildpack or a Dockerfile.
    -   **Ollama:** More complex. Might require a dedicated worker dyno with a custom Docker image and significant resources, or hosting Ollama externally (e.g., on a VPS) and having the Heroku app call its API.
    -   **Database:** Use Heroku Postgres or other JawsDB MySQL add-ons.

### B. Virtual Private Server (VPS)

Using a VPS (e.g., from DigitalOcean, Linode, AWS EC2, Google Cloud VM) gives you full control over the server environment.

1.  **Setup:**
    -   Provision a VPS with sufficient RAM, CPU, and disk space (especially for Ollama models).
    -   Install Java, Maven (optional, if building on the VPS), MySQL Server, and Docker.
    -   Set up a firewall (e.g., UFW).

2.  **Deploying Spring Boot JAR:**
    -   Copy your built Spring Boot JAR (e.g., `bookstore-0.0.1-SNAPSHOT.jar`) to the VPS.
    -   Run it using `java -jar your-app.jar`.
    -   For production, run it as a service (e.g., using `systemd`) so it restarts on failure and runs in the background.
    -   Configure a reverse proxy like Nginx or Apache to handle incoming HTTP(S) requests, manage SSL, and forward requests to the Spring Boot application (usually running on a port like 8080).

3.  **Deploying Ollama:**
    -   **Docker (Recommended):**
        -   Pull the Ollama Docker image: `docker pull ollama/ollama`
        -   Run Ollama in a Docker container, ensuring it has access to necessary resources and potentially mounting a volume for model storage to persist models across container restarts:
            ```bash
            docker run -d --gpus=all -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
            ```
            (The `--gpus=all` flag is for GPU acceleration if available; remove if using CPU only).
        -   Pull the required models into the Ollama container: `docker exec -it ollama ollama pull llama3`
    -   **Direct Install (Alternative):** Install Ollama directly on the VPS following its official documentation.

4.  **Database:**
    -   Install and configure MySQL Server directly on the VPS.
    -   Alternatively, use a managed database service from a cloud provider and connect your Spring Boot application to it.

5.  **Configuration:**
    -   Ensure your Spring Boot `application.properties` points to the correct MySQL database URL and the Ollama API URL (e.g., `http://localhost:11434/api/generate` if Ollama is on the same VPS, or the VPS's public/private IP if on a different machine accessible to the Spring Boot app).
    -   Set up environment variables for sensitive configurations.

### General Steps for Deploying Spring Boot JAR and Ollama Together:

1.  **Package Application:** Build your Spring Boot application into an executable JAR: `mvn clean package`.
2.  **Choose Hosting Environment:** Select a PaaS or VPS based on your needs (cost, control, scalability).
3.  **Deploy Database:** Set up your MySQL database (managed service or self-hosted).
4.  **Deploy Ollama Service:**
    -   Install Ollama (ideally via Docker) on your chosen server/service.
    -   Download the required LLM (e.g., `llama3`).
    -   Ensure Ollama is running and accessible via its API endpoint (default `http://localhost:11434`).
5.  **Deploy Spring Boot Application:**
    -   Upload the JAR file.
    -   Configure `application.properties` (or environment variables) to connect to your deployed database and the Ollama service API.
    -   Run the JAR file (e.g., `java -jar your-app.jar`).
    -   Set up a reverse proxy (Nginx/Apache) if using a VPS for SSL termination, load balancing (if needed), and easier port management.
6.  **Networking & Security:**
    -   Ensure the Spring Boot application can reach the Ollama API endpoint and the database.
    -   Configure firewalls and security groups appropriately.
    -   Set up HTTPS.

## 8. Suggestions for Future Improvements

-   **Advanced NLP for Intent Recognition:** Replace simple keyword matching with a more robust NLP library/service for intent recognition and entity extraction (e.g., book titles, author names). This would make the chatbot more flexible and accurate.
-   **User Authentication for Chat:** Integrate user authentication with the chatbot so it can provide personalized responses based on user history or preferences, and securely manage cart operations tied to a specific user account.
-   **Contextual Conversations:** Implement context management in the chatbot to remember previous parts of the conversation for more natural interactions.
-   **Quantity for "Add to Cart":** Allow users to specify quantity when adding books to the cart via chat (e.g., "add 2 copies of Atomic Habits to my cart").
-   **More Chatbot Actions:**
    -   Remove items from cart.
    -   Check order status.
    -   Provide more detailed book information (e.g., summary, reviews if available).
-   **Refine `extractBookName()`:** The current book name extraction is basic. Improve it to handle more complex titles and sentence structures.
-   **Inventory Management:** Implement proper decrementing of stock when a book is added to cart or an order is placed.
-   **Admin Panel:** A dedicated interface for administrators to manage books, users, and orders.
-   **Payment Gateway Integration:** For a complete e-commerce experience.
-   **Enhanced UI/UX:** Further improvements to the overall website and chatbot UI.
-   **Error Handling:** More granular error handling and user feedback in the chatbot interactions.
-   **Testing:** Add more comprehensive unit and integration tests, especially for the chatbot interaction flows.