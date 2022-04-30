package com.sistema.universitario.exceptions;

public class EnderecoJaCadastradoException extends RuntimeException{
    public EnderecoJaCadastradoException(){super("Endereço já cadastrado no sistema");}
}
