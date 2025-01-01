package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

public class StudentFoundException extends RuntimeException{
    public StudentFoundException(){
        super("E-mail ou Username de Estudante ja cadastrado.");
    }
}
