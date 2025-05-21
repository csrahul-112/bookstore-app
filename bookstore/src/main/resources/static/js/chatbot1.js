document.addEventListener("DOMContentLoaded", () => {
  const chatHeader = document.getElementById("chat-header");
  const chatMessages = document.getElementById("chat-messages");
  const chatInput = document.getElementById("chat-input");

  const books = [
    { name: "The Hobbit", authors: "J.R.R. Tolkien", genre: "fantasy", price: 10 },
    { name: "1984", authors: "George Orwell", genre: "dystopian", price: 12 },
    { name: "The Da Vinci Code", authors: "Dan Brown", genre: "mystery", price: 15 },
    { name: "Pride and Prejudice", authors: "Jane Austen", genre: "romance", price: 8 },
    { name: "To Kill a Mockingbird", authors: "Harper Lee", genre: "drama", price: 11 },
    { name: "Brave New World", authors: "Aldous Huxley", genre: "dystopian", price: 13 },
    { name: "The Great Gatsby", authors: "F. Scott Fitzgerald", genre: "classic", price: 10 },
    { name: "Murder on the Orient Express", authors: "Agatha Christie", genre: "mystery", price: 9 },
    { name: "Dune", authors: "Frank Herbert", genre: "science fiction", price: 14 },
    { name: "Jane Eyre", authors: "Charlotte Brontë", genre: "romance", price: 10 },
    { name: "The Catcher in the Rye", authors: "J.D. Salinger", genre: "classic", price: 11 },
    { name: "The Shining", authors: "Stephen King", genre: "horror", price: 13 },
    { name: "The Alchemist", authors: "Paulo Coelho", genre: "philosophical", price: 10 },
    { name: "Atomic Habits", authors: "James Clear", genre: "self-help", price: 16 },
    { name: "Sapiens", authors: "Yuval Noah Harari", genre: "history", price: 18 },
    { name: "The Martian", authors: "Andy Weir", genre: "science fiction", price: 12 },
    { name: "Gone Girl", authors: "Gillian Flynn", genre: "thriller", price: 14 },
    { name: "Educated", authors: "Tara Westover", genre: "memoir", price: 15 }
  ];

  function addMessage(text, sender = "bot") {
    const message = document.createElement("div");
    message.style.margin = "10px 0";
    message.style.padding = "8px 12px";
    message.style.borderRadius = "12px";
    message.style.maxWidth = "80%";
    message.style.clear = "both";

    if (sender === "user") {
      message.style.backgroundColor = "#007bff";
      message.style.color = "white";
      message.style.float = "right";
      message.textContent = text;
    } else {
      message.style.backgroundColor = "#f1f0f0";
      message.style.color = "black";
      message.style.float = "left";
      message.textContent = text;
    }

    chatMessages.appendChild(message);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }

  function searchBook(query) {
    query = query.toLowerCase();
    return books.find(book => book.name.toLowerCase().includes(query));
  }

  function handleUserMessage(message) {
    const normalized = message.replace(/[^a-z0-9 ]/gi, '').toLowerCase();
    const cleaned = normalized
      .replace(/(who wrote|author of|details about|tell me about|price of|how much|add to cart|add)/g, '')
      .trim();

    if (/(hello|hi|hey)/.test(normalized)) {
      return "Hi! How can I help you today?";
    }

    if (/recommend|suggest/.test(normalized)) {
      const genres = ["fantasy", "mystery", "dystopian", "romance", "science fiction"];
      for (const genre of genres) {
        if (normalized.includes(genre)) {
          return `Here are some great ${genre} books I can recommend.`;
        }
      }
      return "What genre are you interested in?";
    }

    if (/who wrote|author of/.test(normalized)) {
      const book = searchBook(cleaned);
      return book ? `${book.name} is written by ${book.authors}.` : "Sorry, I couldn't find that book.";
    }

    if (/details about|tell me about/.test(normalized)) {
      const book = searchBook(cleaned);
      return book ? `${book.name} is a ${book.genre} novel by ${book.authors}.` : "Sorry, I couldn't find that book.";
    }

    if (/price of|how much/.test(normalized)) {
      const book = searchBook(cleaned);
      return book ? `${book.name} costs $${book.price}.` : "Sorry, I couldn't find that book.";
    }

    if (/add.*(to cart)?/.test(normalized)) {
      const book = searchBook(cleaned);
      return book ? `${book.name} has been added to your cart.` : "Sorry, I couldn't find that book.";
    }

    return "I'm not sure how to help with that.";
  }

  // Toggle chat on header click
  chatHeader.addEventListener("click", () => {
    const isHidden = chatMessages.style.display === "none";
    chatMessages.style.display = isHidden ? "block" : "none";
    chatInput.parentElement.style.display = isHidden ? "block" : "none";
  });

  // Init state: collapsed
  chatMessages.style.display = "none";
  chatInput.parentElement.style.display = "none";

  // Handle Enter key to send message
  chatInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      const userMsg = chatInput.value.trim();
      if (!userMsg) return;

      addMessage(userMsg, "user");
      chatInput.value = "";

      const reply = handleUserMessage(userMsg);
      addMessage(reply, "bot");
    }
  });
});
