package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    // User Story 1: Our API should be able to process new User registrations.
    @PostMapping("/register")
    public ResponseEntity<Account> registerNewAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return ResponseEntity.status(200).body(account);
    }

    // User Story 2: Our API should be able to process User logins.
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account returnedAccount = accountService.login(account);
        return ResponseEntity.status(200).body(returnedAccount);
    }

    // User Story 3: Our API should be able to process the creation of new messages.
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResponseEntity.status(200).body(message);
    }

    // User Story 4: Our API should be able to retrieve all messages.
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messageList = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messageList);
    }

    // User Story 5: Our API should be able to retrieve a message by its ID.
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        return messageService.getMessageById(messageId)
            .map(message -> ResponseEntity.status(200).body(message))
            .orElse(ResponseEntity.status(200).build());
    }

    // User Story 6: Our API should be able to delete a message identified by a message ID.
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        if (messageService.deleteMessageById(messageId)) {
            return ResponseEntity.status(200).body(1);
        }

        return ResponseEntity.status(200).build();
    }

    // User Story 7: Our API should be able to update a message text identified by a message ID.
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Map<String, String> requestBody) {
        messageService.updateMessageById(messageId, requestBody.get("messageText"));
        return ResponseEntity.status(200).body(1);
    }

    // User Story 8: Our API should be able to retrieve all messages written by a particular user.
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser(@PathVariable int accountId) {
        List<Message> messageList = messageService.getAllMessagesByUser(accountId);
        return ResponseEntity.status(200).body(messageList);
    }

}
