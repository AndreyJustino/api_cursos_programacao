package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStudentResponseDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.AuthStundentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service.AuthStudentService;
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
@RequestMapping("/student")
@Tag(name = "Estudante")
public class AuthStudentController {

    @Autowired
    private AuthStudentService authStudentService;

    @PostMapping("/auth")
    @Operation(summary = "Autenticar estudante", description = "Este endpoint é responsavel por autenticar as credenciais do usuario e retornar um token JWT")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthStudentResponseDTO.class))
    })
    public ResponseEntity<Object> authStudentController(@Valid @RequestBody AuthStundentDTO authStundentDTO){
        try {

            AuthStudentResponseDTO result = this.authStudentService.execute(authStundentDTO);

            return ResponseEntity.ok().body(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
