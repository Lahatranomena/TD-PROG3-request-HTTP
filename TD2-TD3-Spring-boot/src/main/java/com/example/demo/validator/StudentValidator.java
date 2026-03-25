package com.example.demo.validator;

import com.example.demo.entity.Student;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {
    public void validate(List<Student> students) {
        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("The reference cannot be null or empty");
            }
            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new BadRequestException("The firstname cannot be null or empty");
            }
            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new BadRequestException("The lastname cannot be null or empty");
            }
        }
    }
}
