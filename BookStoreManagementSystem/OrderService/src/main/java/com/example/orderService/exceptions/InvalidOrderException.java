package com.example.orderService.exceptions;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String s){
        super(s);
    }
}
