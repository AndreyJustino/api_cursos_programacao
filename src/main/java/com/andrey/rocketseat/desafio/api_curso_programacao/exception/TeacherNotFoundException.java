package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException() {
        super("Professor não encontrado.");
    }
}
