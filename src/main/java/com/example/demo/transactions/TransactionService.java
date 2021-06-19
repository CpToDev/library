package com.example.demo.transactions;


import com.example.demo.Student.Student;
import com.example.demo.Student.StudentService;
import com.example.demo.books.Book;
import com.example.demo.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;



    public void issueBook(int student_id,int book_id){

        // update book table with student
        // insert transaction detail into its table

        Book book=bookService.getBookById(book_id);
        Student student=studentService.getStudentById(student_id);
        if(book!=null&&student!=null&&book.getStudent()==null){

                    // setting student_id in book table
                    bookService.issueBook(student_id,book_id);
                    Transaction transaction=Transaction.builder()
                            .book(book)
                            .student(student)
                            .transactionType(TransactionType.ISSUE)
                            .transactionId(UUID.randomUUID().toString())
                            .fine(0)
                            .build();

                    transactionRepository.save(transaction);
            }

    }
    public void returnBook(int student_id,int book_id){


        Book book=bookService.getBookById(book_id);
        Student student=studentService.getStudentById(student_id);
        if(book!=null&&student!=null&&student.getId()==book.getStudent().getId()){

            // setting student_id in book table
            bookService.returnBook(book_id);
            Transaction transaction=Transaction.builder()
                    .book(book)
                    .student(student)
                    .transactionType(TransactionType.RETURN)
                    .transactionId(UUID.randomUUID().toString())
                    .fine(10)
                    .build();

            transactionRepository.save(transaction);
        }

    }


}
