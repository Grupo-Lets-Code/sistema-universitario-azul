package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Curso implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome do curso não informado!")
    @Column(nullable = false)
    private String nomeCurso;
    @NotNull(message = "Turno não informado!")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @ManyToMany
    @JoinColumn(name = "disciplina_id", nullable = false)
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Curso(String nomeCurso, Turno turno) {
        this.nomeCurso = nomeCurso;
        this.turno = turno;
    }

    public Curso(Long id, String nomeCurso, Turno turno) {
        this.id = id;
        this.nomeCurso = nomeCurso;
        this.turno = turno;
    }
}
