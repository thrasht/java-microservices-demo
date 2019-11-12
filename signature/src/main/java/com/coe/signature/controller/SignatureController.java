package com.coe.signature.controller;

import com.coe.signature.model.Signature;
import com.coe.signature.repository.SignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("/")
public class SignatureController {

    @Autowired
    SignatureRepository signatureRepository;

    @GetMapping("signature")
    public ResponseEntity<List<Signature>> getAllSignatures() {
        List signatureList = new ArrayList();

        signatureRepository.findAll().forEach(signatureList::add);

        return new ResponseEntity<List<Signature>>(signatureList, HttpStatus.OK);
    }

    @PostMapping("signature")
    public ResponseEntity<?> createSignature(@RequestBody Signature signature) {
        signatureRepository.save(signature);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("signature/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Signature signature) {
        Optional<Signature> currentStudent = signatureRepository.findById(id);

        if (currentStudent.isPresent() == false) {
            return new ResponseEntity<String>("Unable to update signature with id " + id, HttpStatus.NOT_FOUND);
        }

        Signature updatedSignature = currentStudent.get();
        updatedSignature.setName(signature.getName());

        signatureRepository.save(updatedSignature);

        return new ResponseEntity<Signature>(updatedSignature, HttpStatus.OK);
    }

    @DeleteMapping("signature/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        Signature currentSignature = signatureRepository.findById(id).get();

        if (currentSignature == null) {
            return new ResponseEntity("Unable to delete signature with id " + id, HttpStatus.NOT_FOUND);
        }

        signatureRepository.deleteById(id);
        return new ResponseEntity<Signature>(HttpStatus.NO_CONTENT);
    }

}
