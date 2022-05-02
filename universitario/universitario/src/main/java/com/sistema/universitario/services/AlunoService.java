package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.AlunoNaoEncontradoException;
import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final TurmaService turmaService;

    public AlunoService(AlunoRepository alunoRepository, TurmaService turmaService) {
        this.alunoRepository = alunoRepository;
        this.turmaService = turmaService;

    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public void save(Aluno aluno) {
        alunoRepository.save(aluno);
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
