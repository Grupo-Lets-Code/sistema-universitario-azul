package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @NotBlank(message = "Rua não informada")
    @Column(nullable = false)
    private String rua;

    @NotBlank(message = "Número não informado")
    @Column(nullable = false)
    private String num;

    @NotBlank(message = "Cidade não informada")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Bairro não informado")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "CEP não informado")
    @Column(nullable = false)
    private String cep;

    @OneToOne
    @JoinColumn(name="Usuario_ID", nullable = false)
    private Usuario usuario;

}
