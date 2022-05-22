package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.*;
import com.sistema.universitario.repositories.AlunoRepository;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.repositories.TurmaRepository;
import com.sistema.universitario.services.TurmaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TurmaServiceTest {

    @InjectMocks
    private TurmaService turmaService;

    @Mock
    private TurmaRepository turmaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Mock
    private ProfessorRepository professorRepository;

    Turma turmaBase, turmaBase2;

    @BeforeEach
    void initTurmas() {

        turmaBase = new Turma();
        turmaBase.setId(10L);
        turmaBase.setNome("Turma D4");
        turmaBase.setAluno(new ArrayList<Aluno>());
        turmaBase.setProfessor(new ArrayList<Professor>());
        turmaBase.setDisciplina(new ArrayList<Disciplina>());

        turmaBase2 = new Turma(11L, "Turma E5", new ArrayList<Professor>(), new ArrayList<Aluno>(), new ArrayList<Disciplina>());

    }

    @Test
    @DisplayName("Teste: TURMA01 - Recuperar lista de turmas")
    void getListaTurmasTest() {

        List<Turma> listaTurmasBase = new ArrayList<>();
        listaTurmasBase.add(turmaBase);
        listaTurmasBase.add(turmaBase2);

        when(turmaRepository.findAll()).thenReturn(listaTurmasBase);

        List<Turma> listaTurmasTeste = turmaService.findAllTurmas();

        Assertions.assertNotNull(listaTurmasTeste);
        Assertions.assertFalse(listaTurmasTeste.isEmpty());
        Assertions.assertEquals(2, listaTurmasTeste.size());
        Assertions.assertEquals(listaTurmasBase.get(0), listaTurmasTeste.get(0));
        Assertions.assertEquals(listaTurmasBase.get(1), listaTurmasTeste.get(1));

    }

    @Test
    @DisplayName("Teste: TURMA02 - Recuperar turma por id")
    void getTurmaByIdTest() {

        when(turmaRepository.findById(10L))
            .thenReturn(Optional.of(turmaBase));

        Turma turmaTeste = turmaService.findTurmaById(10L);

        Assertions.assertNotNull(turmaTeste);
        Assertions.assertEquals(turmaBase.getId(), turmaTeste.getId());
        Assertions.assertEquals(turmaBase.getNome(), turmaTeste.getNome());
        Assertions.assertEquals(turmaBase.getAluno(), turmaTeste.getAluno());
        Assertions.assertEquals(turmaBase.getDisciplina(), turmaTeste.getDisciplina());
        Assertions.assertEquals(turmaBase.getProfessor(), turmaTeste.getProfessor());

    }

    @Test
    @DisplayName("Teste: TURMA03 - Salvar turma")
    void saveTurmaTest() {

        when(turmaRepository.save(turmaBase))
            .thenReturn(turmaBase);

        Turma turmaTeste = turmaService.saveTurma(turmaBase);

        Assertions.assertNotNull(turmaTeste);
        Assertions.assertEquals(turmaBase.getId(), turmaTeste.getId());
        Assertions.assertEquals(turmaBase.getNome(), turmaTeste.getNome());
        Assertions.assertEquals(turmaBase.getAluno(), turmaTeste.getAluno());
        Assertions.assertEquals(turmaBase.getDisciplina(), turmaTeste.getDisciplina());
        Assertions.assertEquals(turmaBase.getProfessor(), turmaTeste.getProfessor());

    }

    @Test
    @DisplayName("Teste: TURMA04 - Atualizar turma")
    void updateTurmaTest() {

        when(turmaRepository.findById(turmaBase.getId()))
            .thenReturn(Optional.of(turmaBase));
        when(turmaRepository.save(turmaBase))
            .thenReturn(turmaBase);

        Turma turmaTeste = turmaService.updateNomeTurma(turmaBase.getId(),"Turma A8");

        Assertions.assertNotNull(turmaTeste);
        Assertions.assertEquals(turmaBase.getId(), turmaTeste.getId());
        Assertions.assertEquals("Turma A8", turmaTeste.getNome());
        Assertions.assertEquals(turmaBase.getAluno(), turmaTeste.getAluno());
        Assertions.assertEquals(turmaBase.getDisciplina(), turmaTeste.getDisciplina());
        Assertions.assertEquals(turmaBase.getProfessor(), turmaTeste.getProfessor());

    }

    @Test
    @DisplayName("Teste: TURMA05 - Remover Turma")
    void deleteTurmaTest() {

        when(turmaRepository.findById(turmaBase.getId()))
            .thenReturn(Optional.of(turmaBase));

        doNothing()
            .when(turmaRepository).delete(turmaBase);

        turmaService.deleteTurma(turmaBase.getId());

        verify(turmaRepository, times(1))
            .delete(turmaBase);

    }

    @Test
    @DisplayName("Teste: TURMA06 - Adicionar turma_aluno")
    void addTurmaAlunoTest() {

        Aluno aluno0 = new Aluno(5L, new Usuario(), "Aluno teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);
        Aluno aluno1 = new Aluno(6L, new Usuario(), "Aluno teste 1", "11122233344", new Endereco(), StatusUsuario.ATIVO);
        Aluno aluno2 = new Aluno(7L, new Usuario(), "Aluno teste 2", "22233344455", new Endereco(), StatusUsuario.ATIVO);

        when(alunoRepository.findById(aluno0.getId()))
            .thenReturn(Optional.of(aluno0));

        when(alunoRepository.findById(aluno1.getId()))
            .thenReturn(Optional.of(aluno1));

        when(alunoRepository.findById(aluno2.getId()))
            .thenReturn(Optional.of(aluno2));

        when(turmaRepository.findById(turmaBase.getId()))
            .thenReturn(Optional.of(turmaBase));

        turmaService.addTurmaAluno(10L, 5L);
        turmaService.addTurmaAluno(10L, 6L);
        turmaService.addTurmaAluno(10L, 7L);

        Assertions.assertNotNull(turmaBase.getAluno());
        Assertions.assertFalse(turmaBase.getAluno().isEmpty());
        Assertions.assertEquals(aluno0, turmaBase.getAluno().get(0));
        Assertions.assertEquals(aluno1, turmaBase.getAluno().get(1));
        Assertions.assertEquals(aluno2, turmaBase.getAluno().get(2));
        Assertions.assertEquals(3, turmaBase.getAluno().size());

    }

    @Test
    @DisplayName("Teste: TURMA07 - Remover turma_aluno")
    void removeTurmaAlunoTest() {

        Aluno aluno0 = new Aluno(5L, new Usuario(), "Aluno teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);
        Aluno aluno1 = new Aluno(6L, new Usuario(), "Aluno teste 1", "11122233344", new Endereco(), StatusUsuario.ATIVO);
        Aluno aluno2 = new Aluno(7L, new Usuario(), "Aluno teste 2", "22233344455", new Endereco(), StatusUsuario.ATIVO);

        when(alunoRepository.findById(aluno0.getId()))
            .thenReturn(Optional.of(aluno0));

        when(alunoRepository.findById(aluno1.getId()))
            .thenReturn(Optional.of(aluno1));

        when(alunoRepository.findById(aluno2.getId()))
            .thenReturn(Optional.of(aluno2));

        when(turmaRepository.findById(turmaBase.getId()))
            .thenReturn(Optional.of(turmaBase));

        turmaService.addTurmaAluno(10L, 5L);
        turmaService.addTurmaAluno(10L, 6L);
        turmaService.addTurmaAluno(10L, 7L);

        Assertions.assertNotNull(turmaBase.getAluno());
        Assertions.assertFalse(turmaBase.getAluno().isEmpty());
        Assertions.assertEquals(aluno0, turmaBase.getAluno().get(0));
        Assertions.assertEquals(aluno1, turmaBase.getAluno().get(1));
        Assertions.assertEquals(aluno2, turmaBase.getAluno().get(2));
        Assertions.assertEquals(3, turmaBase.getAluno().size());

        turmaService.removeTurmaAluno(10L, 5L);
        turmaService.removeTurmaAluno(10L, 6L);
        turmaService.removeTurmaAluno(10L, 7L);

        Assertions.assertNotNull(turmaBase.getAluno());
        Assertions.assertTrue(turmaBase.getAluno().isEmpty());

    }

    @Test
    @DisplayName("Teste: TURMA08 - Adicionar turma_professor")
    void addTurmaProfessorTest() {

        Professor professor0 = new Professor(5L, new Usuario(), "Professor teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);
        Professor professor1 = new Professor(6L, new Usuario(), "Professor teste 1", "11122233344", new Endereco(), StatusUsuario.ATIVO);
        Professor professor2 = new Professor(7L, new Usuario(), "Professor teste 2", "22233344455", new Endereco(), StatusUsuario.ATIVO);

        when(professorRepository.findById(professor0.getId()))
            .thenReturn(Optional.of(professor0));

        when(professorRepository.findById(professor1.getId()))
            .thenReturn(Optional.of(professor1));

        when(professorRepository.findById(professor2.getId()))
            .thenReturn(Optional.of(professor2));

        when(turmaRepository.findById(turmaBase2.getId()))
            .thenReturn(Optional.of(turmaBase2));

        turmaService.addTurmaProfessor(11L, 5L);
        turmaService.addTurmaProfessor(11L, 6L);
        turmaService.addTurmaProfessor(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getProfessor());
        Assertions.assertFalse(turmaBase2.getProfessor().isEmpty());
        Assertions.assertEquals(professor0, turmaBase2.getProfessor().get(0));
        Assertions.assertEquals(professor1, turmaBase2.getProfessor().get(1));
        Assertions.assertEquals(professor2, turmaBase2.getProfessor().get(2));
        Assertions.assertEquals(3, turmaBase2.getProfessor().size());

    }

    @Test
    @DisplayName("Teste: TURMA09 - Remover turma_professor")
    void removeTurmaProfessorTest() {

        Professor professor0 = new Professor(5L, new Usuario(), "Professor teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);
        Professor professor1 = new Professor(6L, new Usuario(), "Professor teste 1", "11122233344", new Endereco(), StatusUsuario.ATIVO);
        Professor professor2 = new Professor(7L, new Usuario(), "Professor teste 2", "22233344455", new Endereco(), StatusUsuario.ATIVO);

        when(professorRepository.findById(professor0.getId()))
            .thenReturn(Optional.of(professor0));

        when(professorRepository.findById(professor1.getId()))
            .thenReturn(Optional.of(professor1));

        when(professorRepository.findById(professor2.getId()))
            .thenReturn(Optional.of(professor2));

        when(turmaRepository.findById(turmaBase2.getId()))
            .thenReturn(Optional.of(turmaBase2));

        turmaService.addTurmaProfessor(11L, 5L);
        turmaService.addTurmaProfessor(11L, 6L);
        turmaService.addTurmaProfessor(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getProfessor());
        Assertions.assertFalse(turmaBase2.getProfessor().isEmpty());
        Assertions.assertEquals(professor0, turmaBase2.getProfessor().get(0));
        Assertions.assertEquals(professor1, turmaBase2.getProfessor().get(1));
        Assertions.assertEquals(professor2, turmaBase2.getProfessor().get(2));
        Assertions.assertEquals(3, turmaBase2.getProfessor().size());

        turmaService.removeTurmaProfessor(11L, 5L);
        turmaService.removeTurmaProfessor(11L, 6L);
        turmaService.removeTurmaProfessor(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getProfessor());
        Assertions.assertTrue(turmaBase2.getProfessor().isEmpty());

    }

    @Test
    @DisplayName("Teste: TURMA10 - Adicionar turma_disciplina")
    void addTurmaDisciplinaTest() {

        Disciplina disciplina0 = new Disciplina(5L, "Disciplina teste 0", new ArrayList<Professor>());
        Disciplina disciplina1 = new Disciplina(6L, "Disciplina teste 1", new ArrayList<Professor>());
        Disciplina disciplina2 = new Disciplina(7L, "Disciplina teste 2", new ArrayList<Professor>());

        when(disciplinaRepository.findById(disciplina0.getId()))
            .thenReturn(Optional.of(disciplina0));

        when(disciplinaRepository.findById(disciplina1.getId()))
            .thenReturn(Optional.of(disciplina1));

        when(disciplinaRepository.findById(disciplina2.getId()))
            .thenReturn(Optional.of(disciplina2));

        when(turmaRepository.findById(turmaBase2.getId()))
            .thenReturn(Optional.of(turmaBase2));

        turmaService.addTurmaDisciplina(11L, 5L);
        turmaService.addTurmaDisciplina(11L, 6L);
        turmaService.addTurmaDisciplina(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getDisciplina());
        Assertions.assertFalse(turmaBase2.getDisciplina().isEmpty());
        Assertions.assertEquals(disciplina0, turmaBase2.getDisciplina().get(0));
        Assertions.assertEquals(disciplina1, turmaBase2.getDisciplina().get(1));
        Assertions.assertEquals(disciplina2, turmaBase2.getDisciplina().get(2));
        Assertions.assertEquals(3, turmaBase2.getDisciplina().size());

    }

    @Test
    @DisplayName("Teste: TURMA11 - Remover turma_disciplina")
    void removeTurmaDisciplinaTest() {

        Disciplina disciplina0 = new Disciplina(5L, "Disciplina teste 0", new ArrayList<Professor>());
        Disciplina disciplina1 = new Disciplina(6L, "Disciplina teste 1", new ArrayList<Professor>());
        Disciplina disciplina2 = new Disciplina(7L, "Disciplina teste 2", new ArrayList<Professor>());

        when(disciplinaRepository.findById(disciplina0.getId()))
            .thenReturn(Optional.of(disciplina0));

        when(disciplinaRepository.findById(disciplina1.getId()))
            .thenReturn(Optional.of(disciplina1));

        when(disciplinaRepository.findById(disciplina2.getId()))
            .thenReturn(Optional.of(disciplina2));

        when(turmaRepository.findById(turmaBase2.getId()))
            .thenReturn(Optional.of(turmaBase2));

        turmaService.addTurmaDisciplina(11L, 5L);
        turmaService.addTurmaDisciplina(11L, 6L);
        turmaService.addTurmaDisciplina(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getDisciplina());
        Assertions.assertFalse(turmaBase2.getDisciplina().isEmpty());
        Assertions.assertEquals(disciplina0, turmaBase2.getDisciplina().get(0));
        Assertions.assertEquals(disciplina1, turmaBase2.getDisciplina().get(1));
        Assertions.assertEquals(disciplina2, turmaBase2.getDisciplina().get(2));
        Assertions.assertEquals(3, turmaBase2.getDisciplina().size());

        turmaService.removeTurmaDisciplina(11L, 5L);
        turmaService.removeTurmaDisciplina(11L, 6L);
        turmaService.removeTurmaDisciplina(11L, 7L);

        Assertions.assertNotNull(turmaBase2.getDisciplina());
        Assertions.assertTrue(turmaBase2.getDisciplina().isEmpty());

    }






}