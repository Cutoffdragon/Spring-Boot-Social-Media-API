package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.BlankUsernameException;
import com.example.exception.LoginFailedException;
import com.example.exception.InvalidPasswordException;
import com.example.exception.UsernameExistsException;
import com.example.repository.AccountRepository;

import java.util.Optional;


@Service
public class AccountService {

    // Declare our injection point
    AccountRepository accountRepository;

    // Constructor Injection
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Method to validate an account
    private void validateAccountFields(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();

        // Check to see if the account exists
        Optional<Account> potentialAccount = accountRepository.findByUsername(username);

        // If the username is invalid, throw an exception stating why the username is invalid
        if(potentialAccount.isPresent()) {
            throw new UsernameExistsException("Thats username is already taken. Please pick a new username.");
        } else if (username.isBlank()) {
            throw new BlankUsernameException("Username cannot be blank.");
        } else if(password.length() < 4) {
            throw new InvalidPasswordException("Password cannot be less than 4 characters. Please create a new password.");
        }

    }

    // Register a new account
    public Account addAccount(Account account) {
        
        // First, validate the account, which will throw the proper exception if the credentials are invalid
        validateAccountFields(account);

        return accountRepository.save(account);

    }

    // Account login
    public Account login(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword())
            .orElseThrow(() -> new LoginFailedException("Invalid Credentials"));
    }

}
