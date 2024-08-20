package com.example.bookService.service;


import com.example.bookService.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.bookService.entity.Book;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id){
        return bookRepository.findById(id);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBookById(String id){
        bookRepository.deleteById(id);
    }

}


