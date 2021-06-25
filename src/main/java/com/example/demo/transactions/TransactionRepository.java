package com.example.demo.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


    @Query(value="select t from Transaction t where t.student.id= :student_id and t.book.id= :book_id and t.transactionType=:transactionType and t.transactionStatus=:transactionStatus order by t.id desc")
    public List<Transaction> getIssueTransactionDate(int student_id, int book_id, TransactionType transactionType, TransactionStatus transactionStatus);
}
