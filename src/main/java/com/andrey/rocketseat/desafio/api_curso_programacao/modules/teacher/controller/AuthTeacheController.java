package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.AuthTeacherRequestDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto.AuthTeacherResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.service.AuthTeacherService;
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
public class AuthTeacheController {

    @Autowired
    private AuthTeacherService authTeacherService;

    @PostMapping("/auth")
    @Operation(summary = "Autenticar professor", description = "Este endpoint é responsavel por autenticar as credenciais do professor que foram fornecidas e retornar um token se tudo estiver correto.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthTeacherResponseDTO.class))
    })
    public ResponseEntity<Object> authTeacheController(@Valid @RequestBody AuthTeacherRequestDTO authTeacherRequestDTO){
        try{

            AuthTeacherResponseDTO result = this.authTeacherService.execute(authTeacherRequestDTO);

            return ResponseEntity.ok().body(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
