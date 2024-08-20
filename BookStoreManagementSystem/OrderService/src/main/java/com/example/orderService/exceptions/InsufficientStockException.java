package com.example.orderService.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String s){
        super(s);
    }

}
