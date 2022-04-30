package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.ProfessorNaoEncontradoException;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final DisciplinaService disciplinaService;

    public ProfessorService(ProfessorRepository professorRepository,
                            DisciplinaService disciplinaService) {
        this.professorRepository = professorRepository;
        this.disciplinaService = disciplinaService;
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    public void update(Long id, Professor professorRequest) {
        Professor professor = findById(id);
        professor.setNome(professorRequest.getNome());
        save(professor);
    }

    public void delete(Long id) {
        Professor professor = findById(id);
        professorRepository.delete(professor);
    }

    public void deleteProfessorDisciplina(Long idProfessor, Long idDisciplina) {
        Professor professor = findById(idProfessor);
        var disciplina = disciplinaService.findById(idDisciplina);
        professor.getDisciplina().remove(disciplina);
        professorRepository.save(professor);
    }

    public void deleteProfessorTurma(Long idProfessor, Long idTurma) {
        Professor professor = findById(idProfessor);
    /*    var turma = turmaService.findById(idTurma);
        professor.getTurma().remove(turma);*/
        professorRepository.save(professor);
    }

    public Professor findById(Long id) {
        return this.professorRepository.findById(id).orElseThrow(ProfessorNaoEncontradoException::new);
    }
}
