package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.endereco.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.endereco.EnderecoJaCadastradoException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class EnderecoAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger(EnderecoAdvice.class);

    @ExceptionHandler
    public ResponseEntity trataEnderecoInexistente(EnderecoInexistenteException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity trataEnderecoJaCadastrado(EnderecoJaCadastradoException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions (MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        LOGGER.error("error message {}", errors);
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        };
    }
