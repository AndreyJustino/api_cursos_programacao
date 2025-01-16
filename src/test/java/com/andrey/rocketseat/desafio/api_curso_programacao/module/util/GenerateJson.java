package com.andrey.rocketseat.desafio.api_curso_programacao.module.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenerateJson {
    public static String execute(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
