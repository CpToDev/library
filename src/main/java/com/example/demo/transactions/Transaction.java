package com.example.demo.transactions;


import com.example.demo.Student.Student;
import com.example.demo.books.Book;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String transactionId= UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn
    private Book book;


    @ManyToOne
    @JoinColumn
    private Student student;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date date;

    private String Message;

    private int fine ;


}
