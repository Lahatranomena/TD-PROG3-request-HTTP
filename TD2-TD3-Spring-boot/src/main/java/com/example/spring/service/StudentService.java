package com.example.spring.service;

import com.example.spring.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }
}
