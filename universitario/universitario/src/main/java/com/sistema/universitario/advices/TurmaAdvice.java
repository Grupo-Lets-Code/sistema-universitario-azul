package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.TurmaNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TurmaAdvice {

    private final Logger log = LoggerFactory.getLogger(CursoAdvice.class);

    @ExceptionHandler
    public ResponseEntity turmaNaoEncontrada(TurmaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        log.error("error message turma não encontrada!");
        return response;
    }

    @ExceptionHandler
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("error message curso {}", errors);
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

}
