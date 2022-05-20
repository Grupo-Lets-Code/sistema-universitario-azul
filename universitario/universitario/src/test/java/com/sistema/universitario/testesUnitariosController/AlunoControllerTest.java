package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.AlunoController;
import com.sistema.universitario.models.*;
import com.sistema.universitario.services.AlunoService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AlunoController.class)
public class AlunoControllerTest {

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retornarSucessoBuscarTodosAlunosAtivos() throws Exception {
        List<Aluno> alunosAtivoList = new ArrayList<>();

        alunosAtivoList.add(new Aluno(1L, (new Usuario()), "Aluno Teste 1",
                "12345678901", (new Endereco()), StatusUsuario.ATIVO));

        alunosAtivoList.add(new Aluno(2L, (new Usuario()), "Aluno Teste 2",
                "65432112345", (new Endereco()), StatusUsuario.ATIVO));

        alunosAtivoList.add(new Aluno(3L, (new Usuario()), "Aluno Teste 3",
                "01020312345", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(alunoService.findAllAtivos(StatusUsuario.ATIVO.name()))
                .thenReturn(alunosAtivoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/aluno/aluno-ativo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status",
                        Matchers.equalTo(String.valueOf(StatusUsuario.ATIVO))));
    }

    @Test
    void retornarSucessoBuscarTodosAlunos() throws Exception {
        List<Aluno> AlunosList = new ArrayList<>();

        AlunosList.add(
                new Aluno(1L, (new Usuario()), "Aluno Teste 1",
                        "123456", (new Endereco()), StatusUsuario.INATIVO));

        AlunosList.add(
                new Aluno(2L, (new Usuario()), "Aluno Teste 2",
                        "654321", (new Endereco()), StatusUsuario.INATIVO));

        AlunosList.add(
                new Aluno(3L, (new Usuario()), "Aluno Teste 3",
                        "010203", (new Endereco()), StatusUsuario.ATIVO));

        Mockito.when(alunoService.findAll()).thenReturn(AlunosList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/aluno/todos-alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void cadastrarComSucessoUmAluno() throws Exception{
        var aluno = new Aluno(1L, (new Usuario()), "Aluno Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        Mockito.when(alunoService.save(ArgumentMatchers.any())).thenReturn(aluno);
        String json = objectMapper.writeValueAsString(aluno);

        mockMvc.perform(MockMvcRequestBuilders.post("/aluno/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Aluno(a) criado(a) com sucesso!")));
    }

    @Test
    void deletarComSucessoUmAluno() throws Exception{
        var aluno = new Aluno(1L, (new Usuario()), "aluno Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        var professorDeletado = new Aluno(aluno.getId(), aluno.getUsuario(),
                aluno.getNome(), aluno.getCpf(), aluno.getEndereco(),
                StatusUsuario.INATIVO);

        alunoService.update(aluno.getId(), professorDeletado);
        String json = objectMapper.writeValueAsString(professorDeletado);

        mockMvc.perform(MockMvcRequestBuilders.delete("/aluno/deletar-aluno/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Aluno(a) excluído(a) com sucesso!")));
    }

    @Test
    void atualizarComSucessoUmAluno() throws Exception{
        var aluno = new Aluno(1L, (new Usuario()), "Professor Teste 1",
                "123456", (new Endereco()), StatusUsuario.ATIVO);

        var aluno2 = new Aluno(aluno.getId(), aluno.getUsuario(),
                "Professor Teste 2", aluno.getCpf(), aluno.getEndereco(),
                aluno.getStatus());

        alunoService.update(aluno.getId(), aluno2);
        String json = objectMapper.writeValueAsString(aluno2);

        mockMvc.perform(MockMvcRequestBuilders.put("/aluno/atualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Aluno(a) atualizado(a) com sucesso!")));
    }

}
