package com.example.school.controller;

import com.example.school.dto.StudentRequestDTO;
import com.example.school.dto.StudentResponseDTO;
import com.example.school.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        log.debug("REST request to create student {}", studentDTO);
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudents() {
        log.debug("REST request to get all students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable("id") Long id) {
        log.debug("REST request to get student by id {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable Long id, @RequestBody StudentRequestDTO studentDTO) {
        log.debug("REST request to update student {}", studentDTO);
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long id) {
        log.debug("REST request to delete student by id {}", id);
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
