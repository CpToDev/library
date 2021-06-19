package com.example.demo.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse(null);
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void removeStudent(int id){
        Student student=studentRepository.getOne(id);
        studentRepository.delete(student);
    }
    public void updateStudent(String email,String name){
        studentRepository.updateEmailJPQL(email,name);
    }
    public void deleteStudent(String name){
        studentRepository.deleteStudentbyName(name);
    }

}
