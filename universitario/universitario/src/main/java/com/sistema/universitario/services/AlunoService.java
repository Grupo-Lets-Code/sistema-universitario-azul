package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.AlunoNaoEncontradoException;
import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public List<Aluno> findAllAtivos(String statusUsuario) {
        return alunoRepository.findBy(statusUsuario);
    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void update(Long id, Aluno alunoRequest){
        Aluno aluno = findAlunoById(id);
        aluno.setNome(alunoRequest.getNome());
        save(aluno);
    }
    public void deleteAlunoStatus(Long id){
        Aluno aluno = findAlunoById(id);
        aluno.setStatus(StatusUsuario.INATIVO);
        save(aluno);
    }

    private Aluno findAlunoById(Long id) {
        return this.alunoRepository.findById(id).orElseThrow(AlunoNaoEncontradoException::new);
    }


}
