package com.banking.app.bankingappdemo.exception;

public class StatementNotFoundException extends RuntimeException{
    public StatementNotFoundException(String message){
        super(message);
    }
}
