package com.sistema.universitario.exceptions;

public class DisciplinaProfessorNaoEncontradaException extends RuntimeException {
    public DisciplinaProfessorNaoEncontradaException() {
        super("Disciplina Professor não encontrada");
    }
}
