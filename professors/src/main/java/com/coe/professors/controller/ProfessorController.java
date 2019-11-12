package com.coe.professors.controller;

import com.coe.professors.model.Professor;
import com.coe.professors.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("/")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("professor")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List professorList = new ArrayList();

        professorRepository.findAll().forEach(professorList::add);

        return new ResponseEntity<List<Professor>>(professorList, HttpStatus.OK);
    }

    @PostMapping("professor")
    public  ResponseEntity<?> createProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("professor/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Professor professor) {
        Optional<Professor> currentStudent = professorRepository.findById(id);

        if (currentStudent.isPresent() == false) {
            return new ResponseEntity<String>("Unable to update professor with id " + id, HttpStatus.NOT_FOUND);
        }

        Professor updatedProfessor = currentStudent.get();
        updatedProfessor.setName(professor.getName());
        updatedProfessor.setEmail(professor.getEmail());
        updatedProfessor.setAge(professor.getAge());

        professorRepository.save(updatedProfessor);

        return new ResponseEntity<Professor>(updatedProfessor, HttpStatus.OK);
    }

    @DeleteMapping("professor/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        Professor currentProfessor = professorRepository.findById(id).get();

        if (currentProfessor == null) {
            return new ResponseEntity("Unable to delete professor with id " + id, HttpStatus.NOT_FOUND);
        }

        professorRepository.deleteById(id);
        return new ResponseEntity<Professor>(HttpStatus.NO_CONTENT);
    }

}
