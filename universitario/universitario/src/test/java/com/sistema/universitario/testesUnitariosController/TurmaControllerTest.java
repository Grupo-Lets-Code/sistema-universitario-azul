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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void getAllTurmasTest() throws Exception {

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
    void getTurmaByIdTest() throws Exception {

        when(turmaService.findTurmaById(turmaBase.getId()))
            .thenReturn(turmaBase);

        mockMvc.perform(get("/turmas/10"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isFound());

    }

    @Test
    void saveTurmaTest() throws Exception {

        String turmaBaseJson = objectMapper.writeValueAsString(turmaBase);
        when(turmaService.saveTurma(turmaBase))
            .thenReturn(turmaBase);

        mockMvc.perform(
            post("/turmas/salvar-turma")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(turmaBaseJson)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$",
                Matchers.equalTo("Turma criada com sucesso!")));

    }




}
