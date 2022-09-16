package com.example.school.controller;

import com.example.school.dto.TeacherRequestDTO;
import com.example.school.dto.TeacherResponseDTO;
import com.example.school.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/teachers")
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO teacher) {
        log.debug("REST request to create a teacher {}", teacher);
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<List<TeacherResponseDTO>> getTeachers() {
        log.debug("REST request to get all teachers");
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping(value = "/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable("id") Long id) {
        log.debug("REST request to get teacher by id {}", id);
        return ResponseEntity.ok(teacherService.getTeacherByID(id));
    }

    @PutMapping(value = "/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDTO teacher) {
        log.debug("REST request to update a teacher {}", teacher);
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacher));
    }

    @DeleteMapping(value = "/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id) {
        log.debug("REST request to delete teacher by id {}", id);
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
