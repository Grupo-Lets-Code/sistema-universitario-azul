package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.AlunoNaoEncontradoException;
import com.sistema.universitario.exceptions.ProfessorNaoEncontradoException;
import com.sistema.universitario.exceptions.TurmaNaoEncontradaException;
import com.sistema.universitario.exceptions.disciplina.DisciplinaNaoEncontradaException;
import com.sistema.universitario.models.Turma;
import com.sistema.universitario.repositories.AlunoRepository;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.repositories.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Turma> findAllTurmas() {
        return turmaRepository.findAll();
    }

    public Turma findTurmaById(Long idTurma) {
        return turmaRepository.findById(idTurma).orElseThrow(TurmaNaoEncontradaException::new);
    }

    public Turma saveTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma updateNomeTurma(Long idTurma, String nomeTurma) {
        Turma turma = findTurmaById(idTurma);
        turma.setNome(nomeTurma);
        turmaRepository.save(turma);
        return turma;
    }

    public Turma deleteTurma(Long idTurma) {
        Turma turma = findTurmaById(idTurma);
        turmaRepository.delete(turma);
        return turma;
    }

    public Turma addTurmaAluno(Long idTurma, Long idAluno){
        var aluno = alunoRepository.findById(idAluno).orElseThrow(AlunoNaoEncontradoException::new);
        Turma turma = this.findTurmaById(idTurma);
        turma.getAluno().add(aluno);
        this.saveTurma(turma);
        return turma;
    }

    public Turma removeTurmaAluno(Long idTurma, Long idAluno) {
        var aluno = alunoRepository.findById(idAluno).orElseThrow(AlunoNaoEncontradoException::new);
        Turma turma = this.findTurmaById(idTurma);
        turma.getAluno().remove(aluno);
        this.saveTurma(turma);
        return turma;
    }

    public Turma addTurmaProfessor(Long idTurma, Long idProfessor){
        var professor = professorRepository.findById(idProfessor).orElseThrow(ProfessorNaoEncontradoException::new);
        Turma turma = findTurmaById(idTurma);
        turma.getProfessor().add(professor);
        this.saveTurma(turma);
        return turma;
    }

    public Turma removeTurmaProfessor(Long idTurma, Long idProfessor) {
        var professor = professorRepository.findById(idProfessor).orElseThrow(ProfessorNaoEncontradoException::new);
        Turma turma = findTurmaById(idTurma);
        turma.getProfessor().remove(professor);
        this.saveTurma(turma);
        return turma;
    }

    public Turma addTurmaDisciplina(Long idTurma, Long idDisciplina) {
        var disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(DisciplinaNaoEncontradaException::new);
        Turma turma = findTurmaById(idTurma);
        turma.getDisciplina().add(disciplina);
        this.saveTurma(turma);
        return turma;
    }

    public Turma removeTurmaDisciplina(Long idTurma, Long idDisciplina) {
        var disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(DisciplinaNaoEncontradaException::new);
        Turma turma = findTurmaById(idTurma);
        turma.getDisciplina().remove(disciplina);
        this.saveTurma(turma);
        return turma;
    }

}