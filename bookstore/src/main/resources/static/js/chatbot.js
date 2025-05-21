document.addEventListener('DOMContentLoaded', () => {
  const chatHeader = document.getElementById('chat-header');
  const chatContainer = document.getElementById('chat-container');
  const chatMessages = document.getElementById('chat-messages');
  const chatInput = document.getElementById('chat-input');

  // Toggle chat visibility on header click
  chatHeader.addEventListener('click', () => {
    if (chatMessages.style.display === 'none' || chatMessages.style.display === '') {
      chatMessages.style.display = 'block';
      chatInput.parentElement.style.display = 'block';
    } else {
      chatMessages.style.display = 'none';
      chatInput.parentElement.style.display = 'none';
    }
  });

  // Add message to chat window
  function addMessage(text, sender = 'bot') {
  const message = document.createElement('div');
  message.className = sender === 'user' ? 'chat-message user' : 'chat-message bot';
  message.textContent = text;
  chatMessages.appendChild(message);
  chatMessages.scrollTop = chatMessages.scrollHeight;
}


  // Show typing indicator
  function showTypingIndicator() {
  const typingBubble = document.createElement('div');
  typingBubble.className = 'chat-message bot typing-bubble';
  typingBubble.id = 'typing-dots';
  typingBubble.innerHTML = `
    <div class="typing-indicator">
      <span></span><span></span><span></span>
    </div>
  `;
  chatMessages.appendChild(typingBubble);
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

function removeTypingIndicator() {
  const typing = document.getElementById('typing-dots');
  if (typing) typing.remove();
}


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

// Fuzzy matching for dealing with typos etc
const fuse = new Fuse(books, {
  keys: ['name'],
  threshold: 0.4,     // Lower = stricter; raise to 0.5–0.6 for flexibility
  includeScore: true
});



// Function to figure out what is the intent of onteraction with the bot
function getIntent(message) {
  const normalizedMsg = message.replace(/[^a-z0-9 ]/gi, '').toLowerCase();

  // Create a "cleaned" version for book title matching
  const cleanedMsg = normalizedMsg
    .replace(/(who wrote|author of|details about|tell me about|price of|how much|add to cart|add)/g, '')
    .trim();

  if (/(hello|hi|hey)/.test(normalizedMsg)) {
    return { intent: "greeting" };
  }

  if (/recommend|suggest/.test(normalizedMsg)) {
    const genres = ["fantasy", "mystery", "dystopian", "romance", "science fiction"];
    for (const genre of genres) {
      if (normalizedMsg.includes(genre)) {
        return { intent: "recommend_genre", genre };
      }
    }
    return { intent: "recommend_genre", genre: null };
  }

  if (/who wrote|author of/.test(normalizedMsg)) {
    const results = fuse.search(cleanedMsg);
    const book = results.length ? results[0].item : null;
    return { intent: "author_info", book };
  }

  if (/details about|tell me about/.test(normalizedMsg)) {
    const results = fuse.search(cleanedMsg);
    const book = results.length ? results[0].item : null;
    return { intent: "book_info", book };
  }

  if (/price of|how much/.test(normalizedMsg)) {
    const results = fuse.search(cleanedMsg);
    const book = results.length ? results[0].item : null;
    return { intent: "price_inquiry", book };
  }

  if (/add.*(to cart)?/.test(normalizedMsg)) {
    const results = fuse.search(cleanedMsg);
    const book = results.length ? results[0].item : null;
    return { intent: "add_to_cart", book };
  }

  return { intent: "fallback" };
}




function handleMessage(message) {
  const { intent, genre, book } = getIntent(message);

  switch (intent) {
    case "greeting":
      return "Hello! How can I help you find books today?";
    case "recommend_genre":
      if (genre) {
        const recBooks = books.filter(b => b.genre === genre);
        return recBooks.length
          ? `Here are some great ${genre} books: ${recBooks.map(b => b.name).join(", ")}`
          : `Sorry, we don't have books in the ${genre} genre right now.`;
      } else {
        return `Sure! I can recommend popular books like: ${books.map(b => b.name).join(", ")}`;
      }
    case "author_info":
      return book
        ? `${book.name} was written by ${book.authors}.`
        : "Sorry, I couldn't find the book you're asking about.";
    case "book_info":
      return book
        ? `Details about ${book.name}: Author - ${book.authors}, Genre - ${book.genre}, Price - $${book.price}.`
        : "Sorry, I couldn't find that book.";
    case "price_inquiry":
      return book
        ? `The price of ${book.name} is $${book.price}.`
        : "Sorry, I couldn't find the price for that book.";
    case "add_to_cart":
      return book
        ? `${book.name} has been added to your cart.`
        : "I couldn't find that book to add to your cart.";
    default:
      return "Sorry, I didn't get that. Can you ask differently?";
  }
}


  // Listen for user input
  chatInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter' && chatInput.value.trim() !== '') {
      const userText = chatInput.value.trim();
      addMessage(userText, 'user');
      chatInput.value = '';

      showTypingIndicator();
      // Simulate bot thinking delay
      setTimeout(() => {
        removeTypingIndicator();
        const botResponse = handleMessage(userText);
        addMessage(botResponse, 'bot');
      }, 1000);
    }
  });

  // Initially hide chat messages and input (collapsed)
  chatMessages.style.display = 'none';
  chatInput.parentElement.style.display = 'none';
});
