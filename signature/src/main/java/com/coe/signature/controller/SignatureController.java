package com.coe.signature.controller;

import com.coe.signature.model.Signature;
import com.coe.signature.repository.SignatureRepository;
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

}
