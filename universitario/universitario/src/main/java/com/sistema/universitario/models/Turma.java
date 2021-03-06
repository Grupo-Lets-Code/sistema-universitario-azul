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
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome")
    @NotBlank(message = "Nome não informado")
    private String nome;

    @ManyToMany
    @JoinColumn(name = "Professor_id", nullable = false)
    private List<Professor> professor = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "Aluno_id", nullable = false)
    private List<Aluno> aluno = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "Disciplina_id", nullable = false)
    private List<Disciplina> disciplina = new ArrayList<>();
}