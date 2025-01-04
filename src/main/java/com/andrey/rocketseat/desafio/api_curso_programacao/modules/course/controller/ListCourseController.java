package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.ListCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso")
public class ListCourseController {

    @Autowired
    private ListCourseService listCourseService;

    @GetMapping("/")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    @SecurityRequirement(name = "JWT_Auth")
    @Operation(summary = "Listar cursos cadastrados", description = "Vai listar todos os cursos cadastrados no banco de dados")
    @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = MessageReturnDTO.class)) )
    })
    public ResponseEntity<Object> listCourseController(){

        try{
            MessageReturnDTO messageReturnDTO = MessageReturnDTO.builder()
                    .message("Todos os cursos cadastrados.")
                    .object(listCourseService.execute())
                    .build();

            return ResponseEntity.ok().body(messageReturnDTO);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
