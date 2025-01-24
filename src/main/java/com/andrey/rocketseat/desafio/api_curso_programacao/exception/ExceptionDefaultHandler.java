package com.andrey.rocketseat.desafio.api_curso_programacao.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionDefaultHandler {

    private MessageSource messageSource;

    public ExceptionDefaultHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDefaultHandlerDTO>> exceptionDefaultHandler(MethodArgumentNotValidException e){

        List<ExceptionDefaultHandlerDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(value -> {
            String message = messageSource.getMessage(value, LocaleContextHolder.getLocale());

            ExceptionDefaultHandlerDTO exceptionDefaultHandlerDTO =
                    new ExceptionDefaultHandlerDTO(message, value.getField());

            dto.add(exceptionDefaultHandlerDTO);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException e){

        return ResponseEntity.badRequest().body("O corpo da requisição está mal formatado ou contém dados inválidos.");
    }

}
