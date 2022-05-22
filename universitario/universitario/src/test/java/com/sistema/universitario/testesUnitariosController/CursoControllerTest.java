package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.CursoController;
import com.sistema.universitario.exceptions.CursoNaoEncontradoException;
import com.sistema.universitario.exceptions.disciplina.DisciplinaNaoEncontradaException;
import com.sistema.universitario.models.Curso;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.models.Turno;
import com.sistema.universitario.repositories.CursoRepository;
import com.sistema.universitario.services.CursoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CursoController.class)
public class CursoControllerTest {

    @MockBean
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Autowired
    private MockMvc mockMvc;

    Curso cursoTest;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void iniciaTeste(){
        cursoTest = new Curso();
        cursoTest.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        cursoTest.setTurno(Turno.NOITE);
    }

    @Test
    void deveRetornarSucessoQuandoBuscarTodosCursos() throws Exception {

        List<Curso> cursoList = new ArrayList<>();
        cursoList.add(new Curso(1L, "Engenharia da Computação", Turno.NOITE));
        cursoList.add(new Curso(2L, "Análise e Desenvolvimeto de Sistemas", Turno.NOITE));
        cursoList.add(new Curso(3L, "Ciência da Computação", Turno.NOITE));

        when(cursoService.findAllCursos()).thenReturn(cursoList);

        this.mockMvc.perform(
                get("/cursos/todos-cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].nomeCurso", Matchers.equalTo("Ciência da Computação")));
    }

    @Test
    void deveCriarumNovoCurso() throws Exception{
        var curso = new Curso( 1L,"Engenharia da Computação", Turno.NOITE);

        when(cursoService.saveCurso(ArgumentMatchers.any())).thenReturn(curso);
        String json = objectMapper.writeValueAsString(curso);

        mockMvc.perform(
                post("/cursos/novo-curso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",Matchers.equalTo("Curso criado com sucesso!")));
    }

    @Test
    @DisplayName("deve retornar erro de campo de nome curso")
    void deveRetornarErroDeCampoNome() throws Exception {
        cursoTest.setNomeCurso(null);

        String json = objectMapper.writeValueAsString(cursoTest);

        mockMvc.perform(
               post("/cursos/novo-curso")
                       .contentType(MediaType.APPLICATION_JSON)
                       .characterEncoding("utf-8")
                       .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nomeCurso", Matchers.equalTo("Nome do curso não informado!")));
    }

    @Test
    @DisplayName("Deve retornar um curso por ID")
    void deveRetornarUmCursoPorId() throws Exception {
        var curso = new Curso(1L,"Ciências da Computação", Turno.NOITE);

        when(cursoRepository.findById(curso.getId())).thenReturn(Optional.of(curso));
        when(cursoService.findCursoById(curso.getId())).thenReturn(curso);

        this.mockMvc.perform(
                        get("/cursos/1"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.nomeCurso", Matchers.equalTo("Ciências da Computação")));
    }

    @Test
    @DisplayName("deve retornar erro de curso nao encontrado")
    void deveRetornarCursoNaoEncontrado() throws Exception {
        cursoTest.setId(20L);

        when(cursoRepository.findById(cursoTest.getId())).thenReturn(Optional.empty());
        when(cursoService.findCursoById(cursoTest.getId())).thenThrow(CursoNaoEncontradoException.class);
        mockMvc.perform(
    get("/cursos/{id}", cursoTest.getId()))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof CursoNaoEncontradoException))
            .andExpect(status().isNotFound());
}


    @Test
    @DisplayName("teste de delete na controller")
    void deveAtualizarDadosDeUmCurso() throws Exception {
        cursoTest.setId(1L);

        var cursoAtualizado = new Curso();
        cursoAtualizado.setId(cursoTest.getId());
        cursoAtualizado.setNomeCurso("Engenharia da Computação");
        cursoAtualizado.setTurno(Turno.MANHA);

        when(cursoService.updateCurso(cursoTest.getId(), cursoAtualizado)).thenReturn(cursoAtualizado);

        String json = objectMapper.writeValueAsString(cursoAtualizado);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.equalTo("Curso alterado com sucesso!")));
    }

    @Test
    @DisplayName("delete curso")
    void deveExcluirCurso() throws Exception {
        cursoTest.setId(2L);

        MvcResult requestResult =
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cursos/deletar/2"))
                .andExpect(status().isOk())
                .andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();

        Assertions.assertEquals("Curso excluído com sucesso!", result);
        verify(cursoService, times(1)).deleteCurso(cursoTest.getId());
    }

    @Test
    @DisplayName("adicionar disciplinas no curso")
    void deveAdicionarDisciplinasAoCurso() throws Exception {
        cursoTest.setId(1L);

        var disciplina = new Disciplina(1L, "Estrutura de Dados", null);

        var cursoAtual = new Curso(cursoTest.getId(), cursoTest.getNomeCurso(), cursoTest.getTurno());
        cursoAtual.getDisciplinas().add(disciplina);

        when(cursoService.addCursoDisciplina(cursoTest.getId(), disciplina.getId()))
                .thenReturn(cursoAtual);
        mockMvc.perform(
                post("/cursos/add-disciplina")
                        .param("idCurso", String.valueOf(cursoTest.getId()))
                        .param("idDisciplina", String.valueOf(disciplina.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Disciplina adicionado no curso " + cursoAtual.getNomeCurso() + " com sucesso!")));
    }

    @Test
    @DisplayName("teste excluir disciplina de curso")
    void deveExcluirDisciplinaDeCurso() throws Exception{
        var disciplina = new Disciplina(1L, "Lógica de Programação", null);
        cursoTest.getDisciplinas().add(disciplina);
        cursoTest.setId(1L);

        var cursoAtualizado = cursoTest;
        cursoAtualizado.getDisciplinas().remove(disciplina);

        when(cursoService.deleteCursoDisciplina(cursoTest.getId(), disciplina.getId())).
                thenReturn(cursoAtualizado);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cursos/del-disciplina")
                        .param("idCurso", String.valueOf(disciplina.getId()))
                        .param("idDisciplina", String.valueOf(disciplina.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        Matchers.equalTo("Disciplina excluída do curso " + cursoAtualizado.getNomeCurso() + "com sucesso!")));
    }

    @Test
    @DisplayName("Deve retornar erro ao buscar disciplina")
    void deveRetornarErroAoBuscarDisciplina() throws Exception {
        var disciplina = new Disciplina(1L, "Banco de Dados", null);
        cursoTest.setId(1L);

        when(cursoService.addCursoDisciplina(cursoTest.getId(), disciplina.getId()))
                .thenThrow(DisciplinaNaoEncontradaException.class);
        mockMvc.perform(
                        post("/cursos/add-disciplina")
                .param("idCurso", String.valueOf(cursoTest.getId()))
                .param("idDisciplina", String.valueOf(disciplina.getId())))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DisciplinaNaoEncontradaException))
                .andExpect(status().isNotFound());
    }
}
