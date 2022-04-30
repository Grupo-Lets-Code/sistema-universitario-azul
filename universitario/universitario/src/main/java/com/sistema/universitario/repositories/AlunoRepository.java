package com.sistema.universitario.repositories;

import com.sistema.universitario.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "select * from Aluno where Status = ?1", nativeQuery = true)
    List<Aluno> findByAlunosAtivos(String statusUsuario);
}
