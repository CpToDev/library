package com.example.demo.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping("/books")
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }
    @PostMapping("/book")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }



}
