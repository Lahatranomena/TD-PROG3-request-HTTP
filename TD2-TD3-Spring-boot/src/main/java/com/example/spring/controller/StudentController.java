package com.example.spring.controller;


import com.example.spring.entity.Student;
import com.example.spring.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public List<Student> addStudents(@RequestBody List<Student> newStudents) {
        return studentService.addStudents(newStudents);
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudents(
            @RequestHeader("Accept") String accept) {

        if (accept.equals("text/plain")) {
            List<String> names = studentService.getStudents();
            String result = String.join("\n", names);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Format non supporté");
        }
    }
}
