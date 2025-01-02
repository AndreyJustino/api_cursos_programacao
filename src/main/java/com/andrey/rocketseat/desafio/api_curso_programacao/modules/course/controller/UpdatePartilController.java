package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.CourseEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto.MessageReturnDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.service.UpdatePartilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso")
public class UpdatePartilController {
    @Autowired
    private UpdatePartilService updatePartilService;

    @PatchMapping("/{id}/{status}")
    @Operation(summary = "Atualizara status do curso", description = "Atualizara status de um curso pelo ID fornecido pela URL, assim como o status (ativados e desativado) tambem fornecido pela URL")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MessageReturnDTO.class))
    })
    public ResponseEntity<Object> updatePartilController(@PathVariable UUID id, @PathVariable String status){
        try{

            Status enumStatus = Status.valueOf(status.toUpperCase());

            CourseEntity courseEntity = updatePartilService.updatePartilService(id, enumStatus);

            MessageReturnDTO messageReturnDTO = MessageReturnDTO.builder()
                    .message("Statu do curso atualizado com sucesso!")
                    .object(courseEntity)
                    .build();

            return ResponseEntity.ok().body(messageReturnDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
