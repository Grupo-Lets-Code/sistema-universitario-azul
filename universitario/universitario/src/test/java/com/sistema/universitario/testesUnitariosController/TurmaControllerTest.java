package com.sistema.universitario.testesUnitariosController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.TurmaController;
import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.Turma;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;



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
    void addTurmaAlunoTest() {








    }









}