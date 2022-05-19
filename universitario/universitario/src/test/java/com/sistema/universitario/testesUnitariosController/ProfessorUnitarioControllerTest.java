package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.ProfessorController;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.services.ProfessorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProfessorController.class)
public class ProfessorUnitarioControllerTest {

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retornarSucessoBuscarTodosProfessores() throws Exception {
        List<Professor> professoresList = new ArrayList<>();

        professoresList.add(
                new Professor(1L, (new Usuario()), "Professor Teste 1",
                        "123456", (new Endereco()), StatusUsuario.INATIVO));

        professoresList.add(
                new Professor(2L, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.INATIVO));

        professoresList.add(
                new Professor(3L, (new Usuario()), "Professor Teste 3",
                        "010203", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorService.findAll()).thenReturn(professoresList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/professor/listar-todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void retornarSucessoBuscarTodosProfessoresAtivos() throws Exception {
        List<Professor> professoresAtivoList = new ArrayList<>();

        professoresAtivoList.add(new Professor(1L, (new Usuario()), "Professor Teste 1",
                        "123456", (new Endereco()), StatusUsuario.ATIVO));

        professoresAtivoList.add(new Professor(2L, (new Usuario()), "Professor Teste 2",
                        "654321", (new Endereco()), StatusUsuario.ATIVO));

        professoresAtivoList.add(new Professor(3L, (new Usuario()), "Professor Teste 3",
                        "010203", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(professorService.findAllAtivos(String.valueOf(StatusUsuario.ATIVO)))
                .thenReturn(professoresAtivoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/professor/listar-ativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status",
                        Matchers.equalTo(String.valueOf(StatusUsuario.ATIVO))));
    }

    @Test
    void retornarSucessoBuscarProfessorPorID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/professor/encontrar/1"))
                .andExpect(status().isOk());
    }

    @Test
    void cadastrarComSucessoUmProfessor() throws Exception{
        var professor = new Professor(1L, (new Usuario()), "Professor Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        Mockito.when(professorService.save(ArgumentMatchers.any())).thenReturn(professor);
        String json = objectMapper.writeValueAsString(professor);

        mockMvc.perform(MockMvcRequestBuilders.post("/professor/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Professor(a) criado(a) com sucesso!")));
    }

    @Test
    void atualizarComSucessoUmProfessor() throws Exception{
        var professor = new Professor(1L, (new Usuario()), "Professor Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        var professor2 = new Professor(professor.getId(), professor.getUsuario(),
                "Professor Teste 2", professor.getCpf(), professor.getEndereco(),
                professor.getStatus());

        professorService.update(professor.getId(), professor2);
        String json = objectMapper.writeValueAsString(professor2);

        mockMvc.perform(MockMvcRequestBuilders.put("/professor/atualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Professor(a) alterado(a) com sucesso!")));
    }

    @Test
    void deletarComSucessoUmProfessor() throws Exception{
        var professor = new Professor(1L, (new Usuario()), "Professor Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        var professorDeletado = new Professor(professor.getId(), professor.getUsuario(),
                professor.getNome(), professor.getCpf(), professor.getEndereco(),
                StatusUsuario.INATIVO);

        professorService.update(professor.getId(), professorDeletado);
        String json = objectMapper.writeValueAsString(professorDeletado);

        mockMvc.perform(MockMvcRequestBuilders.delete("/professor/deletar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Professor(a) excluído(a) com sucesso!")));
    }
}
