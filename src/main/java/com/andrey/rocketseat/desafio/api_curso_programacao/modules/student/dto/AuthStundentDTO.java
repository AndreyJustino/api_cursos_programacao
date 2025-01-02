package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthStundentDTO {

    @NotBlank(message = "O campo (email) não deve estar vazio")
    @Email(message = "O email deve estar em um formato valido")
    @Schema(example = "andrey@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "O campo (password) não deve estar vazio.")
    @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
