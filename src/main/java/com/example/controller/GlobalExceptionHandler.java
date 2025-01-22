package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.BlankUsernameException;
import com.example.exception.InvalidAccountIdException;
import com.example.exception.InvalidMessageException;
import com.example.exception.InvalidPasswordException;
import com.example.exception.LoginFailedException;
import com.example.exception.UsernameExistsException;

// This class is to handle our custom exceptions and return status codes based on the exception
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BlankUsernameException.class)
    public ResponseEntity<String> handleBlankUsernameException(BlankUsernameException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<String> handleUsernameExistsException(UsernameExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<String> handleLoginFailedException(LoginFailedException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidAccountIdException.class)
    public ResponseEntity<String> handleInvalidAccountIdException(InvalidAccountIdException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidMessageException.class)
    public ResponseEntity<String> handleInvalidMessageException(InvalidMessageException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }


}
