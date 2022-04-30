package com.sistema.universitario.exceptions;

public class DisciplinaNaoEncontradaException extends RuntimeException {
    public DisciplinaNaoEncontradaException() {
        super("Disciplina não encontrada");
    }
}

