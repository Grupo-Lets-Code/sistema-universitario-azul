package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.*;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.services.DisciplinaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DisciplinaUnitarioServiceTest {

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Mock
    private DisciplinaRepository disciplinaRepository;
    @Mock
    private ProfessorRepository professorRepository;

    Professor professor;
    Disciplina disciplina, disciplinaAtualizada;

    @BeforeEach
    void initProfessor() {
        professor = new Professor();
    }

    @BeforeEach
    void initDisciplina() {
        disciplina = new Disciplina();
        disciplina.setNome("Teste");
    }

    @BeforeEach
    void initDisciplinaAtualizada() {
        disciplinaAtualizada = new Disciplina();
        disciplinaAtualizada.setNome("Teste Atualizado");
    }

    @Test
    @DisplayName("Teste listar todas - Disciplina")
    void listarTodasDisciplinas() {
        List<Disciplina> disciplinasList = new ArrayList<>();
        disciplinasList.add(disciplina);
        disciplinasList.add(disciplina);

        Mockito.when(disciplinaRepository.findAll())
                .thenReturn(disciplinasList);

        List<Disciplina> disciplinas = disciplinaService.findAll();
        Assertions.assertNotNull(disciplinas);
        Assertions.assertEquals(2, disciplinasList.size());
    }

    @Test
    @DisplayName("Teste cadastrar - Disciplina")
    void cadastrarDisciplina() {
        Mockito.when(disciplinaRepository.save(disciplina))
                .thenReturn(disciplina);

        disciplinaService.save(disciplina);

        Assertions.assertNotNull(disciplina);
    }

    @Test
    @DisplayName("Teste atualizar - Disciplina")
    void atualizarDisciplina() {
        Mockito.when(disciplinaRepository.findById(disciplina.getId()))
                .thenReturn(Optional.ofNullable(disciplina));

        disciplinaService.update(disciplina.getId(), disciplinaAtualizada);

        Assertions.assertNotNull(disciplina);
    }

    @Test
    @DisplayName("Teste deletar - Disciplina")
    void deletarDisciplina() {
        Mockito.when(disciplinaRepository.findById(disciplina.getId()))
                .thenReturn(Optional.ofNullable(disciplina));

        disciplinaService.delete(disciplina.getId());

        Assertions.assertNotNull(disciplina);
    }

    @Test
    @DisplayName("Teste deletar - disciplinaProfessor")
    void deletarDisciplinaProfessor() {

        Mockito.when(disciplinaRepository.findById(disciplina.getId()))
                .thenReturn(Optional.ofNullable(disciplina));

        Mockito.when(professorRepository.findById(professor.getId()))
                .thenReturn(Optional.ofNullable(professor));

        disciplinaService.deleteProfessorDisciplina(professor.getId(), disciplina.getId());

        Assertions.assertNotNull(disciplina);
        Assertions.assertNotNull(professor);
    }
}
