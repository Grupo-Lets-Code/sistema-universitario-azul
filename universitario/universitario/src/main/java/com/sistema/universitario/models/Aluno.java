package com.sistema.universitario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Aluno {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;

    @Column (name = "NOME")
    @NotBlank(message = "Nome não informado")
    private String nome;

    @Column (name = "CPF")
    @NotBlank(message = "CPF não informado")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "Endereco_id", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.ATIVO;
}
