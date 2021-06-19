package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    @Autowired
    StudentService studentService;


    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }
    @PostMapping("/student")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }
    @DeleteMapping("/student/{id}")
    public  void removeStudent(@PathVariable int id){
        studentService.removeStudent(id);
    }
    @PutMapping("/student")
    public void updateStudent(@RequestParam String email,@RequestParam String name){
        studentService.updateStudent(email,name);
    }
    @DeleteMapping("/student")
    public void deleteStudent(@RequestParam String name){
        studentService.deleteStudent(name);
    }





}
