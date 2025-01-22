package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidAccountIdException;
import com.example.exception.InvalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    // Define Injection Points
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Method to validate our message
    private void validateMessage(Message message) {
        
        // Check to see if the posted by Id is valid
        accountRepository.findById(message.getPostedBy())
            .orElseThrow(() -> new InvalidAccountIdException("This message was not created by a real user."));

        if(message.getMessageText().trim().isBlank() || message.getMessageText().length() > 255 || message.getMessageText() == null)
            throw new InvalidMessageException("Message must be at least one character and no more than 255 characters.");
            
    }

    // Persist our message to the database
    public Message createMessage(Message message) {
        validateMessage(message);
        return messageRepository.save(message);
    }

    // Get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Get a message by message Id
    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    // Delete a message by Id
    public boolean deleteMessageById(int id) {
        // Check to see if the message exists first
        if (!messageRepository.existsById(id)) {
            return false;
        }

        messageRepository.deleteById(id);
        return true;
    }

    // Update a message
    public void updateMessageById(int id, String messageText) {

        // Check to see if the message exists first, and store it in a Message object if it does
        Message potentialMessage = messageRepository.findById(id)
            .orElseThrow(() -> new InvalidMessageException("There is no message associated with that id."));
        
        potentialMessage.setMessageText(messageText);

        // Call validateMessage to make sure the message text follows our criteria
        validateMessage(potentialMessage);

        // Now save the message
        messageRepository.save(potentialMessage);
    }

    // Get all messages by a specific user
    public List<Message> getAllMessagesByUser(int id) {
        return messageRepository.findAllByPostedBy(id);
    }
}
