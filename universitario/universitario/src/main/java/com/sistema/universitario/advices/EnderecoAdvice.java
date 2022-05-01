package com.sistema.universitario.advices;

import com.sistema.universitario.exceptions.endereco.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.endereco.EnderecoJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EnderecoAdvice {



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

}
