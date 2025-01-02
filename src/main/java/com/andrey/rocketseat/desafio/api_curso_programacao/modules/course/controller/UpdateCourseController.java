package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.UpdateCourseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.UpdateCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso")
public class UpdateCourseController {
    @Autowired
    private UpdateCourseService updateCourseService;

    @PutMapping("/{id}")
    @Operation(summary = "Vai atualizar os dados do curso.", description = "Vai atualizar os dados do curso que ja foi cadastrado anteriormente no banco de dados.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MessageReturnDTO.class))
    })
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
