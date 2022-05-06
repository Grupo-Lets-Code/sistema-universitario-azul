package com.sistema.universitario;

import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.services.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Test
    @DisplayName("Teste listar todos - Professor")
    void listarTodos() {
        List<Professor> professoresList = new ArrayList<>();
        professoresList.add(
                new Professor(1, (new Usuario()), "Professor Teste",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorRepository.findAll())
                .thenReturn(professoresList);

        List<Professor> professores = professorService.findAll();
        Assertions.assertNotNull(professores);
        Assertions.assertFalse(professores.isEmpty());
        Assertions.assertEquals(3, professoresList.size());
    }

    @Test
    @DisplayName("Teste listar todos - Professor")
    void listarAtivos() {
    }

    @Test
    @DisplayName("Teste encontrar - Professor")
    void encontrar() {
    }

    @Test
    @DisplayName("Teste cadastrar - Professor")
    void cadastrar() {
        Professor professorSave = new Professor();
        professorSave.setNome("Teste");
        professorSave.setCpf("12345");
        professorSave.setStatus(StatusUsuario.ATIVO);

        Professor professorReturn = new Professor();
        professorReturn.setId(123);
        professorReturn.setNome("Teste");
        professorReturn.setCpf("12345");
        professorReturn.setStatus(StatusUsuario.ATIVO);

        Mockito.when(professorRepository.save(professorSave))
                .thenReturn(professorReturn);

        professorReturn = professorService.save(professorSave);

        Assertions.assertNotNull(professorReturn);
        //Caso o seu id seja possível ser nulo, descomente a linha abaixo
        //Assertions.assertNotNull(professorReturn.getId());
        Assertions.assertEquals(professorSave.getNome(), professorReturn.getNome());
        Assertions.assertEquals(professorSave.getCpf(), professorReturn.getCpf());
        Assertions.assertEquals(StatusUsuario.ATIVO, professorReturn.getStatus());
    }

    @Test
    @DisplayName("Teste atualizar - Professor")
    void atualizar() {
    }

    @Test
    @DisplayName("Teste deletar - Professor")
    void deletar() {
    }

}
