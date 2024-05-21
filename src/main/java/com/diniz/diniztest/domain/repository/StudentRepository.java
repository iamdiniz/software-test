package com.diniz.diniztest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diniz.diniztest.domain.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
