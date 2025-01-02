package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

public class TeacherFoundExecption extends RuntimeException {
    public TeacherFoundExecption() {
        super("Professor ja cadastrado.");
    }
}
