package com.web.springmvc.budgetmanagement.exception;

public class TransactionErrorException extends RuntimeException{
    public TransactionErrorException(String message) {
        super(message);
    }
}
