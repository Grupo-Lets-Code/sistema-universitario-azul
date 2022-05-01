package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NOME")
    @NotBlank(message = "Nome não informado")
    private String nome;

    @ManyToMany
    @JoinColumn(name = "Professor_id")
    private List<Professor> professor = new ArrayList();

    @ManyToMany
    @JoinColumn(name = "Turma_id", nullable = false)
    private List<Turma> turma = new ArrayList();

    public Disciplina(String nome) {
        this.nome = nome;
    }

}
