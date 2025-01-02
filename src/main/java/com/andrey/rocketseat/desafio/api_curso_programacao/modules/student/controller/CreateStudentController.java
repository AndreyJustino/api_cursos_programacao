package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.controller;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.StudentEntity;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto.CreateStudentDTO;
import com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.service.CreateStudentService;
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
public class CreateStudentController {

    @Autowired
    private CreateStudentService createStudentService;

    @PostMapping("/register")
    @Operation(summary = "Cadastrar estudante", description = "Este endpoint realizara o cadastro do estundante e armazenara os dados dele no banco de dados")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = StudentEntity.class))
    })
    public ResponseEntity<Object> createStudent(@Valid @RequestBody CreateStudentDTO createStudentDTO){
        try {
            StudentEntity result = this.createStudentService.execute(createStudentDTO);

            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
