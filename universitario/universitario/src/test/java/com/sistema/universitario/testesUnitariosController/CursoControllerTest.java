package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.CursoController;
import com.sistema.universitario.models.Curso;
import com.sistema.universitario.models.Turno;
import com.sistema.universitario.services.CursoService;
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
@WebMvcTest(value = CursoController.class)
public class CursoControllerTest {

    @MockBean
    private CursoService cursoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarSucessoQuandoBuscarTodosCursos() throws Exception {

        List<Curso> cursoList = new ArrayList<>();
        cursoList.add(new Curso(1L, "Engenharia da Computação", Turno.NOITE));
        cursoList.add(new Curso(2L, "Análise e Desenvolvimeto de Sistemas", Turno.NOITE));
        cursoList.add(new Curso(3L, "Ciência da Computação", Turno.NOITE));

        Mockito.when(cursoService.findAllCursos()).thenReturn(cursoList);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/cursos/todos-cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].nomeCurso", Matchers.equalTo("Ciência da Computação")));
    }

    @Test
    void deveCriarumNovoCurso() throws Exception{
        var curso = new Curso( 1L,"Engenharia da Computação", Turno.NOITE);

        Mockito.when(cursoService.saveCurso(ArgumentMatchers.any())).thenReturn(curso);
        String json = objectMapper.writeValueAsString(curso);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cursos/novo-curso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",Matchers.equalTo("Curso criado com sucesso!")));
    }


}
