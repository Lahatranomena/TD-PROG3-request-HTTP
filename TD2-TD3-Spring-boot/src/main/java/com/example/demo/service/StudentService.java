package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public String getStudentNames() {
        return students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining("\n"));
    }
}
