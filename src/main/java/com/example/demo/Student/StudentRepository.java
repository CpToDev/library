package com.example.demo.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer>{



    // fiding student by email
    @Query("select s from Student s where s.email=:email")
    public List<Student> findByEmailJPQL(String email);

    @Query(value = "select * from student where email=:email",nativeQuery = true)
    public List<Student> findByEmailSQL(String email);


    //find student by age
    @Query("select s from Student s where age = :age")
    public List<Student> findByAgeJPQL(int age);
    @Query(value = "select * from Student where age =:age",nativeQuery = true)
    public List<Student> findByAgeSQL(int age);


    //update by email
    @Modifying
    @Transactional
    @Query("update Student s set s.email=:email where s.name=:name")
    public void updateEmailJPQL(String email,String name);


    @Modifying
    @Transactional
    @Query("delete from Student s where s.name=:name")
    public void deleteStudentbyName(String name);


}
