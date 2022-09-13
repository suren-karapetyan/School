package com.example.school.controller;

import com.example.school.dao.Student;
import com.example.school.dao.Teacher;
import com.example.school.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1")
public class TeacherController {

    //TODO add logging

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value="/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @GetMapping(value="/teachers")
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping(value="/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherByID(id);
        if (teacher.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(teacher.get());
    }

    @PutMapping(value="/teachers")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher));
    }

    @DeleteMapping(value="/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
