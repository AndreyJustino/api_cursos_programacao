package com.andrey.rocketseat.desafio.api_curso_programacao.modules.course;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ATIVADO,
    DESATIVADO;

    @JsonCreator
    public static Status empty(String status){
        if (status == null || status.trim().isEmpty()){
            return null;
        }

        return Status.valueOf(status.toUpperCase());
    }
}
