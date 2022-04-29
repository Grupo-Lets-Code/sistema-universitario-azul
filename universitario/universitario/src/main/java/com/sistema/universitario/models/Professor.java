package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;

    @Column (name = "NOME")
    private String nome;

    @Column (name = "CPF", unique = true)
    private String cpf;

}
