package com.sistema.universitario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Nome")
    @NotBlank(message = "Nome não informado")
    @JoinColumn(name = "Usuario_email", nullable = false)
    private String nome;

    @Column(name = "Cargo")
    @NotBlank(message = "Nome não informado")
    private String cargo;

    @OneToOne
    @JoinColumn(name = "Usuario_email", nullable = false)
    private Usuario nome_email;
}