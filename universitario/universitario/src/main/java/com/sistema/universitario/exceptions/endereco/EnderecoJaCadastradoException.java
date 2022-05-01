package com.sistema.universitario.exceptions.endereco;

public class EnderecoJaCadastradoException extends RuntimeException{
    public EnderecoJaCadastradoException(){super("Endereço já cadastrado no sistema");}
}
