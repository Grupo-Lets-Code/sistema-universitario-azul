package com.sistema.universitario.repositories;

import com.sistema.universitario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    public Usuario save(Usuario usuario);
}
