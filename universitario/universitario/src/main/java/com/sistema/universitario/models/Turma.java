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

    @ManyToOne
    @JoinColumn(name = "Aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "Disciplina_id")
    private Disciplina disciplina;
}
