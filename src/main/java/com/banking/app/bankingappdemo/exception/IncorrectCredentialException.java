package com.banking.app.bankingappdemo.exception;

public class IncorrectCredentialException extends RuntimeException{
    public IncorrectCredentialException(String message){
        super(message);
    }
}
