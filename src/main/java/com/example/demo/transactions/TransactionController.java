package com.example.demo.transactions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {


    @Autowired
    TransactionService transactionService;

    
    @PostMapping("/issueBook")
    public void issueBook(@RequestParam("student_id")int student_id,@RequestParam("book_id") int book_id){
        transactionService.issueBook(student_id,book_id);
    }
    @PostMapping("/returnBook")
    public void returnBook(@RequestParam("student_id")int student_id,@RequestParam("book_id")int book_id){
        transactionService.returnBook(student_id,book_id);
    }


}
