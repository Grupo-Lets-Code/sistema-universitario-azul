package com.sistema.universitario.repositories;

import com.sistema.universitario.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query(value = "select * from Professor where status = ?1", nativeQuery = true)
    List<Professor> findBy(String statusUsuario);
}
