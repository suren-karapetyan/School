package com.example.school.service;

import com.example.school.domain.Student;
import com.example.school.dto.StudentRequestDTO;
import com.example.school.dto.StudentResponseDTO;
import com.example.school.exception.ApiRequestException;
import com.example.school.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO request) {
        var entity = mapToEntity(request);
        studentRepository.save(entity);
        return mapToDTO(entity);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Student not found", HttpStatus.NOT_FOUND));
        return mapToDTO(entity);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Student not found", HttpStatus.NOT_FOUND));

        Student requestEntity = mapToEntity(request);
        StudentResponseDTO response = mapToDTO(requestEntity);
        response.setId(entity.getId());
        return response;
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    private Student mapToEntity(StudentRequestDTO request) {
        return modelMapper.map(request, Student.class);
    }

    private StudentResponseDTO mapToDTO(Student entity) {
        return modelMapper.map(entity, StudentResponseDTO.class);
    }
}
