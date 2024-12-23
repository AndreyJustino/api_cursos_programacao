package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageReturnDTO {

    private String message;
    private Object object;

}
