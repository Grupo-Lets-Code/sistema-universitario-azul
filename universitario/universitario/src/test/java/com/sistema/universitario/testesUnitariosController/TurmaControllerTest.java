package com.sistema.universitario.testesUnitariosController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.TurmaController;
import com.sistema.universitario.models.*;
import com.sistema.universitario.services.TurmaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TurmaController.class)
public class TurmaControllerTest {

    @MockBean
    private TurmaService turmaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Turma turmaBase, turmaBase2;

    @BeforeEach
    void initTurma() {

        turmaBase = new Turma();
        turmaBase.setId(10L);
        turmaBase.setNome("Turma D4");
        turmaBase.setAluno(new ArrayList<Aluno>());
        turmaBase.setProfessor(new ArrayList<Professor>());
        turmaBase.setDisciplina(new ArrayList<Disciplina>());

    }

    @Test
    @DisplayName("Teste: TURMA01 - Recuperar lista de turmas")
    void getAllTurmasTest() throws Exception {

        turmaBase2 = new Turma(11L, "Turma E5", new ArrayList<Professor>(), new ArrayList<Aluno>(), new ArrayList<Disciplina>());

        List<Turma> listaTurmasTest = new ArrayList<>();

        listaTurmasTest.add(turmaBase);
        listaTurmasTest.add(turmaBase2);

        when(turmaService.findAllTurmas())
            .thenReturn(listaTurmasTest);

        mockMvc.perform(get("/turmas"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Teste: TURMA02 - Recuperar turma por id")
    void getTurmaByIdTest() throws Exception {

        when(turmaService.findTurmaById(turmaBase.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(get("/turmas/10"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isFound());

    }

    @Test
    @DisplayName("Teste: TURMA03 - Salvar turma")
    void saveTurmaTest() throws Exception {

        when(turmaService.saveTurma(turmaBase))
            .thenReturn(turmaBase);

        String turmaBaseJson = objectMapper.writeValueAsString(turmaBase);

        mockMvc.perform(
            post("/turmas/salvar-turma")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(turmaBaseJson)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", Matchers.equalTo("Turma criada com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA04 - Atualizar turma")
    void updateNomeTurmaTest() throws Exception {

        String nomeTurma = "Turma J1";

        when(turmaService.findTurmaById(turmaBase.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(
                put("/turmas/update-turma/" + turmaBase.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content(nomeTurma)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Nome da turma alterado com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA05 - Remover Turma")
    void deleteTurmaTest() throws Exception {

        when(turmaService.deleteTurma(turmaBase.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(delete("/turmas/remove-turma/" + turmaBase.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Turma removida com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA06 - Adicionar turma_aluno")
    void addTurmaAlunoTest() throws Exception {

        Aluno aluno0 = new Aluno(5L, new Usuario(), "Aluno teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);

        when(turmaService.addTurmaAluno(turmaBase.getId(), aluno0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(post("/turmas/add-turma-aluno/" + turmaBase.getId() + "/" + aluno0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Aluno inserido na turma com sucesso!")));


    }

    @Test
    @DisplayName("Teste: TURMA07 - Remover turma_aluno")
    void removeTurmaAlunoTest() throws Exception {

        Aluno aluno0 = new Aluno(5L, new Usuario(), "Aluno teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);

        when(turmaService.removeTurmaAluno(turmaBase.getId(), aluno0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(delete("/turmas/remove-turma-aluno/" + turmaBase.getId() + "/" + aluno0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Aluno removido da turma com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA08 - Adicionar turma_professor")
    void addTurmaProfessorTest() throws Exception {

        Professor professor0 = new Professor(5L, new Usuario(), "Professor teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);

        when(turmaService.addTurmaProfessor(turmaBase.getId(), professor0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(post("/turmas/add-turma-professor/" + turmaBase.getId() + "/" + professor0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Professor inserido na turma com sucesso!")));

    }
    @Test
    @DisplayName("Teste: TURMA09 - Remover turma_professor")
    void removeTurmaProfessorTest() throws Exception {

        Professor professor0 = new Professor(5L, new Usuario(), "Professor teste 0", "00011122233", new Endereco(), StatusUsuario.ATIVO);

        when(turmaService.removeTurmaProfessor(turmaBase.getId(), professor0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(delete("/turmas/remove-turma-professor/" + turmaBase.getId() + "/" + professor0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Professor removido da turma com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA10 - Adicionar turma_disciplina")
    void addTurmaDisciplinaTest() throws Exception {

        Disciplina disciplina0 = new Disciplina(5L, "Disciplina teste 0", new ArrayList<Professor>());

        when(turmaService.addTurmaDisciplina(turmaBase.getId(), disciplina0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(post("/turmas/add-turma-disciplina/" + turmaBase.getId() + "/" + disciplina0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Disciplina inserida na turma com sucesso!")));

    }

    @Test
    @DisplayName("Teste: TURMA11 - Remover turma_disciplina")
    void removeTurmaDisciplinaTest() throws Exception {

        Disciplina disciplina0 = new Disciplina(5L, "Disciplina teste 0", new ArrayList<Professor>());

        when(turmaService.removeTurmaDisciplina(turmaBase.getId(), disciplina0.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(delete("/turmas/remove-turma-disciplina/" + turmaBase.getId() + "/" + disciplina0.getId()))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Disciplina removida da turma com sucesso!")));

    }

}