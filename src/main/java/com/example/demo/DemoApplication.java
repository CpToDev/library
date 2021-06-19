package com.example.demo;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import com.example.demo.books.Book;
import com.example.demo.books.BookRepository;
import com.example.demo.books.Genre;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    public static Logger logger= LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

//	    Student student=Student.builder()
//                .age(23)
//                .email("sauravagtl@gmail.com")
//                .name("saurav shukla")
//                .build();
//	    studentRepository.save(student);
//        Student student2=Student.builder()
//                .age(23)
//                .email("amkt@gmail.com")
//                .name("aman kanti")
//                .build();
//        studentRepository.save(student2);
//
//	    List<Student> studentbyJPQL=studentRepository.findByAgeJPQL(23);
//	    studentbyJPQL.stream().forEach(s->{
//            logger.info("name -{}, age-{} , email-{} ", s.getName(),s.getAge(),s.getEmail());
//	    });
//
//
//
//        Book book= Book.builder()
//                .author("Saurav")
//                .cost(500)
//                .genre(Genre.HORROR)
//                .name("Python")
//                .build();
//        bookRepository.save(book);
//
//        List<Book> booksbyJPQL= bookRepository.findByNameSQL("Python");
//
//        booksbyJPQL.stream().forEach(b->{
//            logger.info("name -{}, author-{}",b.getName(),b.getAuthor());
//        });


    }
}
