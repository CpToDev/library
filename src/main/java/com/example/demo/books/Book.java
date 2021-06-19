package com.example.demo.books;



import com.example.demo.Student.Student;
import com.example.demo.transactions.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String name;
    private String author;
    private int cost ;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy ="book")
    private List<Transaction> transactions;

}
