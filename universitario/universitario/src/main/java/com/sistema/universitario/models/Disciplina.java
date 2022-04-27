package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @OneToMany
    @JoinColumn(name = "professor_id")
    List<Professor> professor;
    //Relacionamento curso disciplina Curso OneToMany Disciplinas
}
