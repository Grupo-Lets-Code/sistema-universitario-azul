package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.CursoNaoEncontradoException;
import com.sistema.universitario.models.Curso;
import com.sistema.universitario.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;


    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso findCursoById(Long id){
        return this.cursoRepository.findById(id).orElseThrow(CursoNaoEncontradoException::new);
    }

    public List<Curso> findAllCursos() { return cursoRepository.findAll();}

    public void saveCurso(Curso curso){
        cursoRepository.save(curso);
    }

    public void updateCurso(Long id, Curso cursoRequest){
        Curso curso = findCursoById(id);
        curso.setNomeCurso(cursoRequest.getNomeCurso());
        curso.setTurno(cursoRequest.getTurno());
        curso.setDisciplinas(cursoRequest.getDisciplinas());
        saveCurso(curso);
    }

    public void deleteCurso(Long id){
        Curso curso = findCursoById(id);
        cursoRepository.delete(curso);
    }


}
