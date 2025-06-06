document.addEventListener('DOMContentLoaded', () => {
    const chatContainer = document.getElementById('chat-container'); // Get the main container
    const chatHeader = document.getElementById('chat-header');
    const chatMessagesContainer = document.getElementById('chat-messages');
    const chatInput = document.getElementById('chat-input');
    const typingIndicator = document.getElementById('typing-indicator');
    const chatInputArea = document.getElementById('chat-input-area');

    if (!chatContainer || !chatHeader || !chatMessagesContainer || !chatInput || !typingIndicator || !chatInputArea) {
        console.error("Chatbot UI elements not found. Please check HTML IDs.");
        return;
    }
    
    // Chat state
    // isChatOpen will be determined by class presence
    let isFirstOpen = true;
    
    // Toggle chat visibility on header click
    chatHeader.addEventListener('click', () => {
        const isOpen = chatContainer.classList.toggle('chat-open');
        
        if (isOpen) {
            // Chat is now open
            if (isFirstOpen) {
                setTimeout(() => {
                    addMessage("👋 Hi there! I'm BookGenie, your personal book assistant. Ask me about book recommendations, genres, or help finding your next great read!", 'bot');
                    if (chatInput) chatInput.focus();
                }, 300); // Delay slightly for animation
                isFirstOpen = false;
            }
             if (typingIndicator) typingIndicator.style.display = 'none'; // Ensure typing indicator is hidden when opening
        } else {
            // Chat is now closed
        }
    });

    // Enhanced message display with support for basic markdown-like formatting
    function addMessage(text, sender = 'bot') {
        const messageElement = document.createElement('div');
        messageElement.className = `chat-message ${sender}`;
        
        // Process text for basic formatting
        const formattedText = text.split('\n').map(line => {
            // Handle bold text with ** or __
            line = line.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>');
            line = line.replace(/__(.*?)__/g, '<strong>$1</strong>');
            
            // Handle italic text with * or _
            line = line.replace(/\*(.*?)\*/g, '<em>$1</em>');
            line = line.replace(/_(.*?)_/g, '<em>$1</em>');
            
            // Handle emoji emphasis
            line = line.replace(/📚|📖|📕|📙|📘|📗|📓|📔|📒|📚/g, '<span class="emoji">$&</span>');
            
            return line;
        });
        
        // Create paragraphs for each line
        formattedText.forEach(line => {
            if (line.trim() === '') return; // Skip empty lines
            
            const p = document.createElement('p');
            p.innerHTML = line; // Using innerHTML since we've processed the text for formatting
            messageElement.appendChild(p);
        });
        
        // Add message with animation
        messageElement.style.opacity = '0';
        messageElement.style.transform = 'translateY(10px)';
        chatMessagesContainer.appendChild(messageElement);
        
        // Trigger animation
        setTimeout(() => {
            messageElement.style.opacity = '1';
            messageElement.style.transform = 'translateY(0)';
        }, 10);
        
        // Scroll to bottom
        chatMessagesContainer.scrollTop = chatMessagesContainer.scrollHeight;
    }

    // Show typing indicator with animation
    function showTypingIndicator() {
        typingIndicator.style.display = 'block';
        typingIndicator.style.opacity = '0';
        
        setTimeout(() => {
            typingIndicator.style.opacity = '1';
            chatMessagesContainer.scrollTop = chatMessagesContainer.scrollHeight;
        }, 10);
    }

    // Hide typing indicator
    function hideTypingIndicator() {
        typingIndicator.style.opacity = '0';
        
        setTimeout(() => {
            typingIndicator.style.display = 'none';
        }, 300);
    }

    // Send message to backend with improved error handling
    async function sendMessageToBackend(userMessage) {
        try {
            const response = await fetch('/api/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ message: userMessage }),
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({ reply: "Error: Could not parse error response from server." }));
                console.error('Error from backend:', response.status, errorData);
                return errorData.reply || `Sorry, there was an error (HTTP ${response.status}). Please try again.`;
            }

            const data = await response.json();
            return data.reply; 
        } catch (error) {
            console.error('Network error or other issue sending message:', error);
            return "Sorry, I couldn't connect to the server. Please check your connection and try again.";
        }
    }

    // Handle user message submission with improved UX
    async function handleUserMessageSubmit(userText) {
        if (userText.trim() === '') return;

        // Add user message
        addMessage(userText, 'user');
        chatInput.value = ''; // Clear input field
        chatInput.disabled = true; // Disable input while waiting for response
        
        // Show typing indicator with slight delay for realism
        setTimeout(() => {
            showTypingIndicator();
        }, 500);

        // Get bot response
        const botReply = await sendMessageToBackend(userText);
        
        // Add slight delay before showing response (feels more natural)
        setTimeout(() => {
            hideTypingIndicator();
            
            setTimeout(() => {
                addMessage(botReply, 'bot');
                chatInput.disabled = false; // Re-enable input
                chatInput.focus(); // Focus back on input
            }, 300);
        }, 1000);
    }

    // Listen for user input (Enter key)
    chatInput.addEventListener('keydown', async (e) => {
        if (e.key === 'Enter' && !chatInput.disabled) {
            e.preventDefault();
            await handleUserMessageSubmit(chatInput.value.trim());
        }
    });
    
    // Handle send button click
    const sendButton = document.getElementById('chat-send-button');
    sendButton.addEventListener('click', async () => {
        if (!chatInput.disabled) {
            await handleUserMessageSubmit(chatInput.value.trim());
        }
    });

    // Add placeholder text cycling for more engagement
    const placeholders = [
        "Ask me about books...",
        "Looking for book recommendations?",
        "Need help finding your next read?",
        "Ask about your favorite genre...",
        "Curious about bestsellers?",
        "Want to discover new authors?"
    ];
    
    let placeholderIndex = 0;
    
    // Change placeholder text every 3 seconds
    setInterval(() => {
        if (!chatInput.value) { // Only change if no user input
            placeholderIndex = (placeholderIndex + 1) % placeholders.length;
            chatInput.setAttribute('placeholder', placeholders[placeholderIndex]);
        }
    }, 3000);

    // Initial state is handled by CSS (chat-container not having 'chat-open' class)
    // Ensure typing indicator is initially hidden if it's not part of the collapsible area
    if (typingIndicator) typingIndicator.style.display = 'none';
});
