package com.example.exception;

public class BlankUsernameException extends RuntimeException{
    
    public BlankUsernameException(String message) {
        super(message);
    }
}
