package com.example.orderService.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String s){
        super(s);
    }
}
