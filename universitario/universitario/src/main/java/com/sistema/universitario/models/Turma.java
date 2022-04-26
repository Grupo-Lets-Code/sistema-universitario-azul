package com.sistema.universitario.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina")
    private Disciplina disciplina;

    @JsonProperty("aluno_id")
    private void unpackNestedAluno(Long aluno_id) {
        this.aluno = new Aluno();
        aluno.setId(aluno_id);
    }

    @JsonProperty("disciplina_id")
    private void unpackNestedDisciplina(Long disciplina_id) {
        this.disciplina = new Disciplina();
        disciplina.setId(disciplina_id);
    }


}
