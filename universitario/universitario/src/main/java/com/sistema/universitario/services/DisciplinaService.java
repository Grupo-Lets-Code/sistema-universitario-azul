package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.DisciplinaNaoEncontradaException;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
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

    public Disciplina findById(Long id) {
        return this.disciplinaRepository.findById(id).orElseThrow(DisciplinaNaoEncontradaException::new);
    }

}
