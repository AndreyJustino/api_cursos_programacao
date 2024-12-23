package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.UpdateCourseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.UpdateCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class UpdateCourseController {
    @Autowired
    private UpdateCourseService updateCourseService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourseController(@PathVariable UUID id, @Valid @RequestBody UpdateCourseDTO updateCourseDTO){
        try{

            CourseEntity result = this.updateCourseService.execute(id, updateCourseDTO);

            MessageReturnDTO messageReturnDTO = MessageReturnDTO.builder()
                    .message("Usuario atualizado com sucesso!")
                    .object(result)
                    .build();

            return ResponseEntity.ok().body(messageReturnDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
