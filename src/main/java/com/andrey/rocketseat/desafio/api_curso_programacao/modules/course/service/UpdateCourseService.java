package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.UpdateCourseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, UpdateCourseDTO updateCourse){

        CourseEntity courseEntityExisting = this.courseRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Curso não encontrado");
        });

        courseEntityExisting.setName(updateCourse.getName());
        courseEntityExisting.setStatus(updateCourse.getStatus());
        courseEntityExisting.setCategory(updateCourse.getCategory());

        return this.courseRepository.save(courseEntityExisting);
    }
}
