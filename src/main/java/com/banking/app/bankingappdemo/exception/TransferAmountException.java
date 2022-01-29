package com.banking.app.bankingappdemo.exception;

public class TransferAmountException extends RuntimeException{
    public TransferAmountException(String message){
        super(message);
    }
}
