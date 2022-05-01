package com.sistema.universitario.exceptions;

public class TurmaNaoEncontradaException extends RuntimeException {

    public TurmaNaoEncontradaException() {
        super("Turma não encontrada!");
    }

}
