package com.sistema.universitario.exceptions.endereco;

public class EnderecoJaCadastradoException extends RuntimeException{
    public EnderecoJaCadastradoException(){super("Endereco ja cadastrado no sistema");}
}
