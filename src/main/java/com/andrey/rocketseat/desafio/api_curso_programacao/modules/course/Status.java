package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ATIVADO,
    DESATIVADO;

    @JsonCreator
    public static Status empty(String level){
        if (level == null || level.trim().isEmpty()){
            return null;
        }

        return Status.valueOf(level.toUpperCase());
    }
}
