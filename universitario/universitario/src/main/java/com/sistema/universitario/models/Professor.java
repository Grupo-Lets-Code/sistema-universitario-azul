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
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @Column (name = "NOME")
    private String nome;

    @Column (name = "CPF", unique = true)
    private String cpf;

}
