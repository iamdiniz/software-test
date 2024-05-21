package com.diniz.diniztest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diniz.diniztest.domain.model.Student;
import com.diniz.diniztest.domain.repository.StudentRepository;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student insert(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

}
