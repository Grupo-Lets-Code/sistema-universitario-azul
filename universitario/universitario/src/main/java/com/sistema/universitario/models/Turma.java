package com.sistema.universitario.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeTurma;

    @OneToMany
    @JoinColumn(name = "Aluno_id")
    private List<Aluno> aluno;

    @ManyToOne
    @JoinColumn(name = "Disciplina_id")
    private Disciplina disciplina;
  
    //Turma OneToMany para alunos
}
