package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseService {

    @Autowired
    private CourseRepository courseRepository;

    public MessageReturnDTO execute(CourseEntity courseEntity){

        this.courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory())
                .ifPresent((value) -> {
                    throw new IllegalArgumentException("Curso ja cadastrado!");
                });

        CourseEntity result = this.courseRepository.save(courseEntity);

        MessageReturnDTO returnDTO = MessageReturnDTO.builder()
                .message("Curso cadastrado com sucesso!")
                .object(result)
                .build();

        return returnDTO;
    }

}
