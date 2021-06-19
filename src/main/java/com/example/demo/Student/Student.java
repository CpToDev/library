package com.example.demo.Student;


import com.example.demo.books.Book;
import com.example.demo.transactions.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String name;
    private int age;
    private String email;


    @OneToMany(mappedBy = "student")
    private List<Book>books;

    @OneToMany(mappedBy ="book")
    private List<Transaction> transactions;
}
