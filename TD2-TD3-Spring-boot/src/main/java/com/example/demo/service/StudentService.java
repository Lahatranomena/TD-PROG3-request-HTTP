package com.example.demo.service;

import com.example.demo.entity.Student;
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

    public List<Student> getAllStudents() {
        return students;
    }

    public String getStudentNames() {
        StringBuilder names = new StringBuilder();
        for (Student student : students) {
            names.append(student.getFirstName())
                    .append(" ")
                    .append(student.getLastName())
                    .append("\n");
        }
        return names.toString();
    }

}
