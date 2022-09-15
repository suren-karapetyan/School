package com.example.school.service;

import com.example.school.domain.Student;
import com.example.school.dto.StudentRequestDTO;
import com.example.school.dto.StudentResponseDTO;
import com.example.school.exception.NotFoundException;
import com.example.school.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<StudentResponseDTO> getStudentById(Long id) {
        //TODO ask if this is a good practice
        StudentResponseDTO response = mapToDTO(studentRepository.findById(id).get());
        return Optional.of(response);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new NotFoundException("Student not found");
        }
        Student student = studentOptional.get();

        Student requestEntity = modelMapper.map(request, Student.class);
        StudentResponseDTO response = modelMapper.map(requestEntity, StudentResponseDTO.class);
        response.setId(student.getId());
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
