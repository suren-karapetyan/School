package com.example.school.service;

import com.example.school.domain.Teacher;
import com.example.school.dto.TeacherRequestDTO;
import com.example.school.dto.TeacherResponseDTO;
import com.example.school.exception.NotFoundException;
import com.example.school.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public TeacherResponseDTO createTeacher(TeacherRequestDTO request) {
        var entity = mapToEntity(request);
        teacherRepository.save(entity);
        return mapToDTO(entity);
    }

    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TeacherResponseDTO> getTeacherByID(Long id) {

        TeacherResponseDTO response = mapToDTO(teacherRepository.findById(id).get());
        return Optional.of(response);
    }

    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO request) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isEmpty()) {
            throw new NotFoundException("Teacher not found");
        }
        Teacher teacher = teacherOptional.get();

        Teacher requestEntity = modelMapper.map(request, Teacher.class);
        TeacherResponseDTO response = modelMapper.map(requestEntity, TeacherResponseDTO.class);
        response.setId(teacher.getId());
        return response;
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    private Teacher mapToEntity(TeacherRequestDTO request) {
        return modelMapper.map(request, Teacher.class);
    }

    private TeacherResponseDTO mapToDTO(Teacher entity) {
        return modelMapper.map(entity, TeacherResponseDTO.class);
    }
}
