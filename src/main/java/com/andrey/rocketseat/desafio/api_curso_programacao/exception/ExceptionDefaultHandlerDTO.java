package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDefaultHandlerDTO {
    private String message;
    private String field;
}
