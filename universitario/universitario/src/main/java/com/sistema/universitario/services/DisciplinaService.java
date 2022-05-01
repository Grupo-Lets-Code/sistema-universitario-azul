package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.disciplina.DisciplinaNaoEncontradaException;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository,
                             ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public void save(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public void update(Long id, Disciplina disciplinaRequest) {
        Disciplina disciplina = findById(id);
        disciplina.setNome(disciplinaRequest.getNome());
        save(disciplina);
    }

    public void delete(Long id) {
        Disciplina disciplina = findById(id);
        disciplinaRepository.delete(disciplina);
    }

    public void deleteProfessorDisciplina(Long idProfessor, Long idDisciplina) {
        Disciplina disciplina = findById(idDisciplina);
        var professor = professorRepository.findById(idProfessor);
        disciplina.getProfessor().remove(professor);
        save(disciplina);
    }

    public Disciplina findById(Long id) {
        return this.disciplinaRepository.findById(id)
                .orElseThrow(DisciplinaNaoEncontradaException::new);
    }
}
