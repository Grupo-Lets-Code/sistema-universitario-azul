package com.sistema.universitario.testesIntegracaoService;

import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.services.EnderecoService;
import com.sistema.universitario.services.ProfessorService;
import com.sistema.universitario.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessorIntegracaoServiceTest {

    @Autowired
    ProfessorService professorService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EnderecoService enderecoService;

    Professor professor;

    @Test
    void listarTodosProfessores() {
        List<Professor> professores = professorService.findAll();
        Assertions.assertNotNull(professores);
    }

    @Test
    void listarTodosProfessoresAtivos() {
        List<Professor> professores = professorService.findAllAtivos(
                String.valueOf(StatusUsuario.ATIVO));
        Assertions.assertNotNull(professores);
    }

    @Test
    void salvarProfessor() {
        var catchUserId = usuarioService.findById(1L);
        var catchEndId = enderecoService.findEnderecoById(1L);

        professor = new Professor();
        professor.setUsuario(catchUserId);
        professor.setNome("Teste Integracao");
        professor.setCpf("12345678921");
        professor.setEndereco(catchEndId);
        professor.setStatus(StatusUsuario.ATIVO);
        professorService.save(professor);

        Assertions.assertNotNull(catchUserId);
        Assertions.assertNotNull(catchEndId);
        Assertions.assertNotNull(professor);
    }

    @Test
    void atualizarProfessor() {
        var professor = professorService.findById(1L);

        professor.setNome("Integracao Atualizado");
        professorService.update(professor.getId(), professor);

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(professor.getNome(), professor.getNome());
    }

    @Test
    void deletarProfessor() {
        var professor = professorService.findById(1L);

        professor.setStatus(StatusUsuario.INATIVO);
        professorService.delete(professor.getId());

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(StatusUsuario.INATIVO, professor.getStatus());
    }
}
