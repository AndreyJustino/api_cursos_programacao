package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.DeleteCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso")
public class DeleteCourseController {
    @Autowired
    private DeleteCourseService deleteCourseService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    @SecurityRequirement(name = "JWT_Auth")
    @Operation(summary = "Remover curso", description = "Vai realizara a remocao do curso do banco de dados, de acordo com o ID fornecido.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(example = "Curso deletado com sucesso!"))
    })
    public ResponseEntity<String> deleteCourseController(@PathVariable UUID id){
        try{

            this.deleteCourseService.execute(id);

            return ResponseEntity.ok().body("Curso deletado com sucesso!");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
