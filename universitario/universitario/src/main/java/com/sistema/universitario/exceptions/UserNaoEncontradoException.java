package com.sistema.universitario.exceptions;

public class UserNaoEncontradoException extends RuntimeException{
    public UserNaoEncontradoException() {
        super("Usuario(a) não encontrado(a)");
    }
}