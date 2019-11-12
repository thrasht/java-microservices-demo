package com.coe.demo.controller;

import com.coe.demo.model.Student;
import com.coe.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Optional<Student> currentStudent = studentRepository.findById(id);

        if (currentStudent.isPresent() == false) {
            return new ResponseEntity<String>("Unable to update Student with id " + id, HttpStatus.NOT_FOUND);
        }

        Student updatedStudent = currentStudent.get();
        updatedStudent.setName(student.getName());
        updatedStudent.setEmail(student.getEmail());

        studentRepository.save(updatedStudent);

        return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        Student currentStudent = studentRepository.findById(id).get();

        if (currentStudent == null) {
            return new ResponseEntity("Unable to delete user with id " + id, HttpStatus.NO_CONTENT);
        }

        studentRepository.deleteById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

}
