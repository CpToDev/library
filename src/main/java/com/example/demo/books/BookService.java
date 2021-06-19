package com.example.demo.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

        @Autowired
        BookRepository bookRepository;

        public List<Book> getAllBooks(){
            return bookRepository.findAll();
        }
        public Book getBookById(int bookId){
            return  bookRepository.findById(bookId).orElse(null);
        }
        public void addBook(Book book){
            bookRepository.save(book);
        }

        public void issueBook(int student_id,int book_id){
            bookRepository.issueBook(student_id,book_id);
        }
        public void returnBook(int book_id){
            bookRepository.returnBook(book_id);
        }

}