package com.example.bookService.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String s){
        super(s);
    }
}
