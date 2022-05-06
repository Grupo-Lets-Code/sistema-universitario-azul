package com.sistema.universitario.models;

import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Turma;
import com.sistema.universitario.models.Usuario;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;

    @Column (name = "NOME")
    private String nome;

    @Column (name = "CPF", unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "Endereco_id", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.ATIVO;
}
