package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTeacherDTO {
    @NotBlank(message = "O campo (name) não pode estar vazio")
    @Schema(example = "Gustavo Guanaba", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O campo (username) não pode estar vazio")
    @Schema(example = "gustavoGuanabara", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email(message = "O campo (email) precisa de um e-mail valido")
    @NotBlank(message = "O campo (email) não pode estar vazio")
    @Schema(example = "guanabara@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(max = 70, min = 8, message = "A senha deve ter no minimo 8 carateres")
    @NotBlank(message = "O campo (password) não pode estar vazio")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
