package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.TurmaNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TurmaAdvice {

    private final Logger log = LoggerFactory.getLogger(CursoAdvice.class);

    @ExceptionHandler
    public ResponseEntity turmaNaoEncontrada(TurmaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        log.error("error message turma não encontrada!");
        return response;
    }

}
