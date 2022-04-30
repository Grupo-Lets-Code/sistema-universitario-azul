package com.sistema.universitario.exceptions;

public class ProfessorNaoEncontradaException extends RuntimeException {
    public ProfessorNaoEncontradaException() {
        super("Professor(a) não encontrado(a)");
    }
}
