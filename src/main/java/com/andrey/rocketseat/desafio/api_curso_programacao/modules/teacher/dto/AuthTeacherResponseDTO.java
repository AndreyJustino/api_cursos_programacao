package com.andrey.rocketseat.desafio.api_curso_programacao.modules.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTeacherResponseDTO {
    private String token;
    private Instant expireAt;
}
