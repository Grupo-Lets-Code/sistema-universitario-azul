package com.sistema.universitario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long id;

    @Column(name = "NOME")
    @NotBlank(message = "Nome não informado")
    private String nome;

    @ManyToMany
    @JoinColumn(name = "Professor_id")
    private List<Professor> professor = new ArrayList();

    public Disciplina(String nome)  {
        this.nome = nome;
    }
}
