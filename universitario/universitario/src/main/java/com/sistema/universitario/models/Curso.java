package com.sistema.universitario.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Adicionar o atributo turno e nomeCurso
    //Relacionamento curso disciplina Curso ManyToMany Disciplinas
    //Curso OneToMany para turmas



}
