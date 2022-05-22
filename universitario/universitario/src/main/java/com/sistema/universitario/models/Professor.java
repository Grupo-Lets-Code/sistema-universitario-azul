package com.sistema.universitario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;

    @Column (name = "NOME")
    private String nome;

    @Column (name = "CPF")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "Endereco_id", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.ATIVO;

}
