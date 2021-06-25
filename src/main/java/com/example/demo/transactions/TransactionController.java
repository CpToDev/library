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
    public String issueBook(@RequestParam("student_id")int student_id,@RequestParam("book_id") int book_id) throws Exception {
        return transactionService.issueBook(student_id,book_id);
    }
    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("student_id")int student_id,@RequestParam("book_id")int book_id) throws Exception {
        return transactionService.returnBook(student_id,book_id);
    }


}
