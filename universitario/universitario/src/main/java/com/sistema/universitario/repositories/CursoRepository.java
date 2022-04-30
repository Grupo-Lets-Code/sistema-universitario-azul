package com.sistema.universitario.repositories;

import com.sistema.universitario.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
