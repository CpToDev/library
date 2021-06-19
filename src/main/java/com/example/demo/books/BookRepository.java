package com.example.demo.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer> {


    @Query(value = "select b from Book b where b.name=:name",nativeQuery = false)
    public List<Book> findByNameJPQL(String name);

    @Query(value = "select * from Book where name=:name",nativeQuery = true)
    public List<Book> findByNameSQL(String name);


    // Assignment

    @Transactional
    @Modifying
    @Query(value ="update book b set b.student_id=:student_id where b.id=:book_id" ,nativeQuery = true)
    public void issueBook(int student_id,int book_id);


    @Transactional
    @Modifying
    @Query(value = "update book b set b.student_id=null where b.id=:book_id",nativeQuery = true)
    public void returnBook(int book_id);
}
