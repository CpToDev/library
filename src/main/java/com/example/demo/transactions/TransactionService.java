package com.example.demo.transactions;


import com.example.demo.Student.Student;
import com.example.demo.Student.StudentService;
import com.example.demo.books.Book;
import com.example.demo.books.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private static Logger logger= LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Value("${book.maxBookAllowed}")
    private String maxBookAllowed;

    @Value("${book.max_allowed_days}")
    private int maxAllowedDays;

    @Value("${book.fine}")
    private String fine;



    public String issueBook(int student_id,int book_id) throws Exception {

        // update book table with student
        // insert transaction detail into its table

        Book book=bookService.getBookById(book_id);
        Student student=studentService.getStudentById(student_id);

        Transaction transaction= Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUE)
                .build();

        if(book==null||book.getStudent()!=null) {

            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("Either the Book Id is invalid or not available");
            transactionRepository.save(transaction);
            throw new Exception("Either the Book Id is invalid or not available");

        }
        if(student==null) {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("Invalid Student Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Student Id");
        }
        if(student.getBooks().size()==new Integer(maxBookAllowed)){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("Max Books Limit Reached");
            transactionRepository.save(transaction);
            throw new Exception("Max Books Limit Reached");
        }

        book.setStudent(student);



        bookService.addBook(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Success");
        transactionRepository.save(transaction);


        return " The transaction is successful with transitionId "+transaction.getTransactionId();


    }
    public String returnBook(int student_id,int book_id) throws Exception {

        Book book=bookService.getBookById(book_id);
        Student student=studentService.getStudentById(student_id);

        Transaction transaction= Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .transactionId(UUID.randomUUID().toString())
                .student(student)
                .book(book)
                .build();

        if(book==null||book.getStudent()==null){

            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("Either book not available or not issued by you");
            throw  new Exception("Unautherized - either book not available or  not issued");

        }
        if(student==null){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("Invalid Student Id");
            throw  new Exception("Invalid Student Id");
        }
        if(book.getStudent().getId()!=student_id){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction.setMessage("This book is not issued by you");
            throw  new Exception("Unauthorized access ");
        }
        book.setStudent(null);
        bookService.addBook(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Book returned successfully");

        List<Transaction> transactions=transactionRepository.getIssueTransactionDate(student_id,book_id,TransactionType.ISSUE,TransactionStatus.SUCCESS);
        Transaction tr=transactions.get(0);
        Date dateOfIssue=tr.getDate();
        long timeInMillis=System.currentTimeMillis()-dateOfIssue.getTime();
        int days=(int) (timeInMillis/(1000*60*60*24));

        if(days>maxAllowedDays)
        transaction.setFine((days-maxAllowedDays)*Integer.parseInt(fine));



        transactionRepository.save(transaction);

        return "The book is returned successfully with transaction Id "+transaction.getTransactionId();
    }


}



