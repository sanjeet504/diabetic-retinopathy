package com.example.bookService.controller;

import com.example.bookService.entity.Book;


import com.example.bookService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        Optional<Book>bookOptional=bookService.getBookById(id);
        if (bookOptional.isPresent()){
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }
    @PutMapping
    public ResponseEntity<Book> updateBook(@PathVariable String id,@RequestBody Book book){
        Optional<Book> bookOptional=bookService.getBookById(id);
        if(bookOptional.isPresent()){
            Book book1=bookOptional.get();
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPrice(book.getPrice());
            book1.setStock(book.getStock());

            return new ResponseEntity<>(bookOptional.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable String id){
        Optional<Book>bookOptional=bookService.getBookById(id);
        if(bookOptional.isPresent()){
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}

