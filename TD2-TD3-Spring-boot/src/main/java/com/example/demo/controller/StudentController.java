package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.StudentService;
import com.example.demo.validator.StudentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final StudentValidator studentValidator;

    public StudentController(StudentService studentService,
                             StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(
            @RequestBody List<Student> newStudents) {
        try {
            studentValidator.validate(newStudents);

            List<Student> result = studentService.addStudents(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getStudents(
            @RequestHeader(value = "Accept", required = false) String accept) {
        try {
            if (accept == null || accept.isBlank()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Missinge 'Accept' header");
            }

            if (accept.equals("text/plain")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(studentService.getStudentNames());
            }

            if (accept.equals("application/json")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(studentService.getAllStudents());
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format not supported");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error server");
        }
    }
}
