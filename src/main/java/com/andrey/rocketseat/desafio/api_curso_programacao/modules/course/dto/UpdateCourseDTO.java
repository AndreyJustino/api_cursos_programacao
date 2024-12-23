package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto;

import com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.Status;
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
    private String name;

    @NotBlank(message = "O campo (categoria) não deve estar vazio")
    private String category;

    @NotNull(message = "O campo (status) não deve estar vazio")
    @Enumerated(EnumType.STRING)
    private Status status;
}
