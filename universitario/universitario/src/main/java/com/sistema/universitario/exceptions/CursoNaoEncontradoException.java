package com.sistema.universitario.exceptions;

public class CursoNaoEncontradoException extends RuntimeException{
    public CursoNaoEncontradoException(){
        super("Curso não encontrado");
    }
}
