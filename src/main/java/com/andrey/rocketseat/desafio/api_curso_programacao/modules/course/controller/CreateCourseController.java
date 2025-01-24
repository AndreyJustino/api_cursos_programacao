package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.CreateCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso")
public class CreateCourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @PostMapping("/")
    @PreAuthorize("hasRole('TEACHER')")
    @SecurityRequirement(name = "JWT_Auth")
    @Operation(summary = "Cadastrar curso", description = "Realiza cadastro de um novo curso, armazenando seus dados no banco de dados")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MessageReturnDTO.class))
    })
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseEntity courseEntity){
        try{

            MessageReturnDTO result = createCourseService.execute(courseEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
