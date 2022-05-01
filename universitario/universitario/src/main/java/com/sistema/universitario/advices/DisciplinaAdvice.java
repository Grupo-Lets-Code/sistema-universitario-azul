package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.CursoNaoEncontradoException;
import com.sistema.universitario.exceptions.DisciplinaNaoEncontradaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DisciplinaAdvice {

    @ExceptionHandler
    public ResponseEntity NotFoundDisciplina(DisciplinaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

}
