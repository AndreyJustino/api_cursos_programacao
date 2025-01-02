package com.andrey.rocketseat.desafio.api_curso_programacao.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthStudentResponseDTO {
    private String token;
    private Instant expireAt;
}
