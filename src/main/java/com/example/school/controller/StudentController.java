package com.example.school.controller;

import com.example.school.domain.Student;
import com.example.school.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "api/v1")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    //TODO remove domains from controller - use dao instead and map dao to domain in service layer.
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        log.debug("REST request to create student {}", student);
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getStudents() {
        log.debug("REST request to get all students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        log.debug("REST request to get student by id {}", id);
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(student.get());
    }

    @PutMapping(value = "/students")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student) {
        log.debug("REST request to update student {}", student);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long id) {
        log.debug("REST request to delete student by id {}", id);
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
