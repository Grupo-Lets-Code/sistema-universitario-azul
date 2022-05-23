package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.DisciplinaController;
import com.sistema.universitario.models.*;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.services.DisciplinaService;
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
@WebMvcTest(value = DisciplinaController.class)
public class DisciplinaUnitarioControllerTest {

    @MockBean
    private DisciplinaService disciplinaService;

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retornarSucessoBuscarTodasDisciplinas() throws Exception {
        List<Disciplina> disciplinasList = new ArrayList<>();

        disciplinasList.add(new Disciplina());
        disciplinasList.add(new Disciplina());
        disciplinasList.add(new Disciplina());

        Mockito.when(disciplinaService.findAll()).thenReturn(disciplinasList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/disciplina/listar-todas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    void cadastrarComSucessoUmaDisciplina() throws Exception {
        var disciplina = new Disciplina("Disciplina Teste 1");

        Mockito.when(disciplinaService.save(ArgumentMatchers.any())).thenReturn(disciplina);
        String json = objectMapper.writeValueAsString(disciplina);

        mockMvc.perform(MockMvcRequestBuilders.post("/disciplina/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Disciplina criada com sucesso!")));
    }

    @Test
    void atualizarComSucessoUmaDisciplina() throws Exception {
        var disciplina = new Disciplina("Disciplina Teste 1");
        var disciplinaNomeAtt = new Disciplina("Disciplina Atualizada");

        disciplinaService.update(disciplina.getId(), disciplinaNomeAtt);
        String json = objectMapper.writeValueAsString(disciplinaNomeAtt);

        mockMvc.perform(MockMvcRequestBuilders.put("/disciplina/atualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Disciplina alterada com sucesso!")));
    }

    @Test
    void deletarComSucessoUmaDisciplina() throws Exception {
        var disciplina = new Disciplina("Disciplina Teste 1");

        disciplinaService.delete(disciplina.getId());
        String json = objectMapper.writeValueAsString(disciplina);

        mockMvc.perform(MockMvcRequestBuilders.delete("/disciplina/deletar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Disciplina excluída com sucesso!")));
    }
}
