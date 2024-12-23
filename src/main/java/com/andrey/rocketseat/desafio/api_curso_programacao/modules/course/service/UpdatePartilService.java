package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePartilService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity updatePartilService(UUID id, Status status){
        CourseEntity courseEntityExiting = this.courseRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("ID fornecido, não encontrado");
        });

        courseEntityExiting.setStatus(status);

        return courseRepository.save(courseEntityExiting);
    }
}
