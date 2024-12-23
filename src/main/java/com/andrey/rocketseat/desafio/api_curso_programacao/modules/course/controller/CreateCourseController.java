package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.CreateCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CreateCourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @PostMapping("/")
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseEntity courseEntity){
        try{

            MessageReturnDTO result = createCourseService.execute(courseEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {

            e.getMessage();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
