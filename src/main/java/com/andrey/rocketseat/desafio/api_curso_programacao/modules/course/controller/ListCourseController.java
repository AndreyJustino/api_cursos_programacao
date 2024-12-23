package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.ListCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class ListCourseController {

    @Autowired
    private ListCourseService listCourseService;

    @GetMapping("/")
    public ResponseEntity<Object> listCourseController(){

        try{
            MessageReturnDTO messageReturnDTO = MessageReturnDTO.builder()
                    .message("Todos os cursos cadastrados.")
                    .object(listCourseService.execute())
                    .build();

            return ResponseEntity.ok().body(messageReturnDTO);

        } catch (Exception e) {

            e.getMessage();

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
