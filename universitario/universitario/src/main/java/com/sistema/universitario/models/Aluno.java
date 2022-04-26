package com.sistema.universitario.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @Column (name = "NOME")
    private String nome;

    @Column (name = "CPF", unique = true)
    private String cpf;

    @Column (name = "RUA")
    private String rua;

    @Column (name = "NUMERO")
    private String num;

    @Column (name = "BAIRRO")
    private String bairro;

    @Column (name = "CIDADE")
    private String cidade;

    @Column (name = "CEP")
    private String cep;
}
