package com.sistema.universitario.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException() {
        super("Usuario(a) não encontrado(a)");
    }
}