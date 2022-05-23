package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.disciplina.DisciplinaNaoEncontradaException;
import com.sistema.universitario.exceptions.disciplina.DisciplinaProfessorNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DisciplinaAdvice {

    private final Logger log = LoggerFactory.getLogger(DisciplinaAdvice.class);

    @ExceptionHandler
    public ResponseEntity NotFoundDisciplina(DisciplinaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        log.error("error message Disciplina não encontrada!");
        return response;
    }

    @ExceptionHandler
    public ResponseEntity NotFoundDisciplinaProfessor(DisciplinaProfessorNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        log.error("error message Professor(a) nessa Disciplina não encontrado(a)!");
        return response;
    }
}
