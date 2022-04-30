package com.sistema.universitario.exceptions;

public class ProfessorNaoEncontradoException extends RuntimeException{
    public ProfessorNaoEncontradoException() {
        super("Professor(a) não encontrado(a)");
    }
}

