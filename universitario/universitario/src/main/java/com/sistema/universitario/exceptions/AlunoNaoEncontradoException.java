package com.sistema.universitario.exceptions;

public class AlunoNaoEncontradoException extends RuntimeException{
    public AlunoNaoEncontradoException() {
        super("Aluno(a) não encontrado(a)");
    }
}
