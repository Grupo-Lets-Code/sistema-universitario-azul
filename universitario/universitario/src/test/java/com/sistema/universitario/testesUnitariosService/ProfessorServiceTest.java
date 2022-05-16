package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.ProfessorRepository;
import com.sistema.universitario.services.ProfessorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    Professor professor, professorAtualizado, professorDeletado;

    @BeforeEach
     void initProfessor() {
        professor = new Professor();
        professor.setUsuario(new Usuario("teste@email.com", "123456"));
        professor.setId(123);
        professor.setNome("Teste");
        professor.setCpf("12345");
        professor.setEndereco(new Endereco());
        professor.setStatus(StatusUsuario.ATIVO);
    }

    @BeforeEach
     void attProfessor() {
        professorAtualizado = new Professor();
        professorAtualizado.setId(professor.getId());
        professorAtualizado.setNome("Teste Atualizado");
        professorAtualizado.setCpf(professor.getCpf());
        professorAtualizado.setStatus(professor.getStatus());
    }

    @BeforeEach
     void delProfessor() {
        initProfessor();
        professorDeletado = new Professor();
        professorDeletado.setId(professor.getId());
        professorDeletado.setNome(professor.getNome());
        professorDeletado.setCpf(professor.getCpf());
        professorDeletado.setStatus(StatusUsuario.INATIVO);
    }


    @Test
    @WithMockUser
    @DisplayName("Teste listar todos - Professor")
    void listarTodosProfessores() {
        List<Professor> professoresList = new ArrayList<>();
        professoresList.add(
                new Professor(1, (new Usuario()), "Professor Teste",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        professoresList.add(
                new Professor(2, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorRepository.findAll())
                .thenReturn(professoresList);

        List<Professor> professores = professorService.findAll();
        Assertions.assertNotNull(professores);
        Assertions.assertEquals(2, professoresList.size());
    }

    @Test
    @WithMockUser
    @DisplayName("Teste listar ativos - Professor")
    void listarProfessoresAtivos() {
        List<Professor> professoresAtivoList = new ArrayList<>();
        professoresAtivoList.add(
                new Professor(1, (new Usuario()), "Professor Teste",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        professoresAtivoList.add(
                new Professor(2, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.INATIVO));

        List<Professor> professores = professorService.findAllAtivos(String
                .valueOf(StatusUsuario.ATIVO));
        Assertions.assertNotNull(professores);
    }

    @Test
    @WithMockUser
    @DisplayName("Teste encontrar por id - Professor")
    void encontrarPorId() {
        Mockito.when(professorRepository.findById(professor.getId()))
                .thenReturn(Optional.of(professor));

        professor = professorService.findById(professor.getId());

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(professor.getId(), professor.getId());
    }

    @Test
    @WithMockUser
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
    @WithMockUser
    @DisplayName("Teste atualizar - Professor")
    void atualizarProfessor() {
        Mockito.when(professorRepository.save(professor))
                .thenReturn(professorAtualizado);

        professorAtualizado = professorService.save(professor);
        sendEmailAtt();

        Assertions.assertNotNull(professor);
        Assertions.assertNotNull(professorAtualizado);
        Assertions.assertNotNull(professor.getNome(), professorAtualizado.getNome());
    }

    @Test
    @WithMockUser
    @DisplayName("Teste deletar - Professor")
    void deletarProfessor() {
        Mockito.when(professorRepository.save(professor))
                .thenReturn(professorDeletado);

        professorDeletado = professorService.save(professor);

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(professor.getId(), professor.getId());
        Assertions.assertEquals(StatusUsuario.ATIVO, professor.getStatus());

        Assertions.assertNotNull(professorDeletado);
        Assertions.assertEquals(professorDeletado.getId(), professorDeletado.getId());
        Assertions.assertEquals(StatusUsuario.INATIVO, professorDeletado.getStatus());
    }

    void sendEmailAtt() {
        System.out.println("\nE-mail: contato@universidadeazul.com " +
                "\nPara: " + professor.getUsuario().getEmail() +
                "\nAssunto: Alerta de alteração na conta" +
                "\nMensagem: Nós da Universidade Azul informamos que, em " + LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " sua conta teve uma alteração.\n"
                + "Caso não tenha sido você, altere sua senha!");
    }
}
