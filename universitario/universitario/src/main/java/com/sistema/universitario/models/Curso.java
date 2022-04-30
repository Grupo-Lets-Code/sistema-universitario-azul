package com.sistema.universitario.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

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
    private Set<Disciplina> disciplinas;

}
