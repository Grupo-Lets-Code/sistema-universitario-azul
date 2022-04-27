package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany
    @JoinColumn(name = "professor_id")
    List<Professor> professor;
    //Relacionamento curso disciplina Curso OneToMany Disciplinas
}
