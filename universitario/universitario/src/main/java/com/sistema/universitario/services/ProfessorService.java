package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.ProfessorNaoEncontradoException;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public List<Professor> findAllAtivos(String statusUsuario) {
        return professorRepository.findBy(statusUsuario);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void update(Long id, Professor professorRequest) {
        Professor professor = findById(id);
        professor.setNome(professorRequest.getNome());
        save(professor);
    }

    public void delete(Long id) {
        Professor professor = findById(id);
        professor.setStatus(StatusUsuario.INATIVO);
        save(professor);
    }

    public Professor findById(Long id) {
        return this.professorRepository.findById(id).orElseThrow(ProfessorNaoEncontradoException::new);
    }
}
