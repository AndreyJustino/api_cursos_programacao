package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute(){
        return this.courseRepository.findAll();
    }
}
