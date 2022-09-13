package com.example.school.controller;

import com.example.school.dao.Student;
import com.example.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="api/v1")
public class StudentController {

    //TODO add logging

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping(value="/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(value="/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(student.get());
    }

    @PutMapping(value="/students")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student) {
       return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping(value="/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
