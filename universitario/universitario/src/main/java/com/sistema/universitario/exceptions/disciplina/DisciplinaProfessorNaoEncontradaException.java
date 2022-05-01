package com.sistema.universitario.exceptions.disciplina;

public class DisciplinaProfessorNaoEncontradaException extends RuntimeException {
    public DisciplinaProfessorNaoEncontradaException() {
        super("Disciplina Professor não encontrada");
    }
}
