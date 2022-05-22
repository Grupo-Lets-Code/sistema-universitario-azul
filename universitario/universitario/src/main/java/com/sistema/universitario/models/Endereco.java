package com.sistema.universitario.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank(message = "Rua não informada")
    @Column(nullable = false)
    private String rua;

    @NotBlank(message = "Número não informado")
    @Column(nullable = false)
    private String num;

    @NotBlank(message = "Cidade não informada")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Bairro não informado")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "CEP não informado")
    @Column(nullable = false)
    private String cep;

    @OneToOne
    @JoinColumn(name="Usuario_ID", nullable = false)
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return id != null && Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}