package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.services.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    Professor professor, professorAtualizado;

    @BeforeEach
    void initProfessor() {
        professor = new Professor();
        professor.setUsuario(new Usuario("teste@email.com", "123456"));
        professor.setId(123L);
        professor.setNome("Teste");
        professor.setCpf("12345");
        professor.setEndereco(new Endereco());
        professor.setStatus(StatusUsuario.ATIVO);
    }

    @BeforeEach
    void attProfessor() {
        professorAtualizado = new Professor();
        professorAtualizado.setUsuario(professor.getUsuario());
        professorAtualizado.setId(professor.getId());
        professorAtualizado.setNome("Teste Atualizado");
        professorAtualizado.setCpf(professor.getCpf());
        professorAtualizado.setEndereco(professor.getEndereco());
        professorAtualizado.setStatus(professor.getStatus());
    }

    @Test
    @DisplayName("Teste listar todos - Professor")
    void listarTodosProfessores() {
        List<Professor> professoresList = new ArrayList<>();
        professoresList.add(
                new Professor(1L, (new Usuario()), "Professor Teste",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        professoresList.add(
                new Professor(2L, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorRepository.findAll())
                .thenReturn(professoresList);

        List<Professor> professores = professorService.findAll();
        Assertions.assertNotNull(professores);
        Assertions.assertEquals(2, professoresList.size());
    }

    @Test
    @DisplayName("Teste listar ativos - Professor")
    void listarProfessoresAtivos() {
        List<Professor> professoresAtivoList = new ArrayList<>();
        professoresAtivoList.add(
                new Professor(1L, (new Usuario()), "Professor Teste",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        professoresAtivoList.add(
                new Professor(2L, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorRepository.findBy("ATIVO"))
                .thenReturn(professoresAtivoList);

        professoresAtivoList = professorService.findAllAtivos(String
                .valueOf(StatusUsuario.ATIVO));

        Assertions.assertNotNull(professoresAtivoList);
    }

    @Test
    @DisplayName("Teste encontrar por id - Professor")
    void encontrarPorId() {
        Mockito.when(professorRepository.findById(professor.getId()))
                .thenReturn(Optional.of(professor));

        professor = professorService.findById(professor.getId());

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(professor.getId(), professor.getId());
    }

    @Test
    @DisplayName("Teste cadastrar - Professor")
    void cadastrarProfessor() {
        Mockito.when(professorRepository.save(professor))
                .thenReturn(professor);

        professor = professorService.save(professor);

        Assertions.assertNotNull(professor);
        Assertions.assertNotNull(professor.getNome());
        Assertions.assertNotNull(professor.getCpf());
        Assertions.assertEquals(StatusUsuario.ATIVO, professor.getStatus());
    }

    @Test
    @DisplayName("Teste atualizar - Professor")
    void atualizarProfessor() {
        Mockito.when(professorRepository.findById(professor.getId()))
                .thenReturn(Optional.ofNullable(professor));

        professorService.update(professor.getId(), professorAtualizado);

        Assertions.assertNotNull(professor);
        Assertions.assertNotNull(professorAtualizado);
        Assertions.assertEquals(professor.getId(), professorAtualizado.getId());
    }

    @Test
    @DisplayName("Teste deletar - Professor")
    void deletarProfessor() {
        Mockito.when(professorRepository.findById(professor.getId()))
                .thenReturn(Optional.ofNullable(professor));

        professorService.delete(professor.getId());

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(StatusUsuario.INATIVO, professor.getStatus());
    }
}
