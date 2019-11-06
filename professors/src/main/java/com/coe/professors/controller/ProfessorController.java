package com.coe.professors.controller;

import com.coe.professors.model.Professor;
import com.coe.professors.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
