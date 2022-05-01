package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.TurmaNaoEncontradaException;
import com.sistema.universitario.models.Turma;
import com.sistema.universitario.repositories.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public void save(Turma turma) {
        turmaRepository.save(turma);
    }


    public Turma findById(Long idTurma) {
        return turmaRepository.findById(idTurma).orElseThrow(TurmaNaoEncontradaException::new);
    }
}
