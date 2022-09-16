package com.example.school.service;

import com.example.school.domain.Teacher;
import com.example.school.dto.TeacherRequestDTO;
import com.example.school.dto.TeacherResponseDTO;
import com.example.school.exception.ApiRequestException;
import com.example.school.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public TeacherResponseDTO getTeacherByID(Long id) {
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Teacher not found", HttpStatus.NOT_FOUND));

        return mapToDTO(entity);
    }

    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO request) {
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Teacher not found", HttpStatus.NOT_FOUND));

        Teacher requestEntity = mapToEntity(request);
        TeacherResponseDTO response = mapToDTO(requestEntity);
        response.setId(entity.getId());
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
