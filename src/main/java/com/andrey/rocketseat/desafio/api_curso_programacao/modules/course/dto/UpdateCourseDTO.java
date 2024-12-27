package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDTO {

    @NotBlank(message = "O campo (nome) não deve estar vazio")
    @Schema(example = "Curso de JavaScript", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "O campo (categoria) não deve estar vazio")
    @Schema(example = "Front-End", requiredMode = Schema.RequiredMode.REQUIRED)
    private String category;

    @NotNull(message = "O campo (status) não deve estar vazio")
    @Enumerated(EnumType.STRING)
    @Schema(example = "DESATIVADO", requiredMode = Schema.RequiredMode.REQUIRED)
    private Status status;
}
