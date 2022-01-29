package com.banking.app.bankingappdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectCredentialException.class)
    public ResponseEntity<Object> handleIncorrectCredentialException(IncorrectCredentialException ex, WebRequest webRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Incorrect Credential");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TransferAmountException.class)
    public ResponseEntity<Object> handleITransferAmountException(TransferAmountException ex, WebRequest webRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Error occurred while transferring amount!! Please verify the details");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DepositAmountException.class)
    public ResponseEntity<Object> handleDepositAmountException(DepositAmountException ex, WebRequest webRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Error occurred while depositing money!! Please verify the details");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
