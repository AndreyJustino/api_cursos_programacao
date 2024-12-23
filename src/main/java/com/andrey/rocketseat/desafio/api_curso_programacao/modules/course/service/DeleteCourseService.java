package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id){

        this.courseRepository.findById(id).orElseThrow(() -> {
           throw new EmptyResultDataAccessException("ID fornecido, não foi encontrado.", 1);
        });

        this.courseRepository.deleteById(id);

    }
}
