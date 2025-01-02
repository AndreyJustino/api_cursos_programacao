package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.TeacherEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.CreateTeacherDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service.CreateTeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@Tag(name = "Professor")
public class CreateTeacheController {

    @Autowired
    private CreateTeacherService createTeacherService;

    @PostMapping("/register")
    @Operation(summary = "Cadastrar professor", description = "Este endpoint é responsavel por cadastrar as credenciais do professor no banco de dados")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TeacherEntity.class))
    })
    public ResponseEntity<Object> createTeacheController(@Valid @RequestBody CreateTeacherDTO createTeacherDTO){
        try{

            TeacherEntity result = this.createTeacherService.execute(createTeacherDTO);

            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
