package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.DeleteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class DeleteCourseController {
    @Autowired
    private DeleteCourseService deleteCourseService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseController(@PathVariable UUID id){
        try{

            this.deleteCourseService.execute(id);

            return ResponseEntity.ok().body("Curso deletado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
