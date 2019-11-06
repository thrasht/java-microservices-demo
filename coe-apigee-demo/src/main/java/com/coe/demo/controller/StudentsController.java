package com.coe.demo.controller;

import com.coe.demo.model.Student;
import com.coe.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("student")
    public List<Student> getAllStudents() {
        List studentList = new ArrayList();
        studentRepository.findAll().forEach(studentList::add);

        return studentList;
    }

    @PostMapping("student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentRepository.save(student);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

}
