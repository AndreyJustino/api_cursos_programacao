package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Estudante não encontrado.");
    }
}
