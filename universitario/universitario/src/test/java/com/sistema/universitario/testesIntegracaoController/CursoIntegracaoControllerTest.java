package com.sistema.universitario.testesIntegracaoController;

import com.sistema.universitario.models.Curso;
import com.sistema.universitario.models.Turno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoIntegracaoControllerTest {

    @LocalServerPort
    Integer port;

    @Autowired
    TestRestTemplate restTemplate;

    Curso cursoTest;

    HttpEntity httpEntity;
    HttpHeaders httpHeaders;

    @BeforeEach
    void iniciaTeste(){
        cursoTest = new Curso();
        cursoTest.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        cursoTest.setTurno(Turno.NOITE);
    }

    @Test
    void salvarCursoControllerIntTest(){
        var curso = new Curso();
        curso.setNomeCurso("Ciência da Computação");
        curso.setTurno(Turno.NOITE);

        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(curso, httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/cursos/novo-curso", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void listarCursosControllerIntegTeste(){
        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Curso[]> response = this.restTemplate
                .exchange("/cursos/todos-cursos", HttpMethod.GET, httpEntity, Curso[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotEquals(0, response.getBody().length);
        Assertions.assertNotNull(response.getBody()[0].getNomeCurso());
    }

    @Test
    void retornarCursoPorIdControllerIntegTest(){
        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/cursos/2", HttpMethod.GET, httpEntity, String.class);

        Assertions.assertEquals(302, response.getStatusCodeValue());
    }


    @Test
    void atualizaDadosDeUmCurnoControllerIntegTest(){
        cursoTest.setId(1L);

        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity<>(cursoTest, httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/cursos/1", HttpMethod.PUT, httpEntity, String.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(cursoTest);
    }

    @Test
    void excluirCursoControllerIntegTest(){
        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/cursos/deletar/1", HttpMethod.DELETE, httpEntity, Void.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void adicionarDisciplinaCursoControllerIntegTest(){
        cursoTest.setNomeCurso("Administracao");

        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(httpHeaders);

        String url = "http://localhost:"+port+"/cursos/add-disciplina";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("idCurso", 2)
                .queryParam("idDisciplina", 1);

        ResponseEntity<String> response = restTemplate
                .exchange(builder.build().encode().toUri(),
                        HttpMethod.POST,
                        httpEntity,
                        String.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @Transactional
    void excluirDisciplinaCursoControllerIntegTest(){
        cursoTest.setNomeCurso("Gestao");

        httpHeaders = new HttpHeaders();
        httpEntity = new HttpEntity(httpHeaders);

        String url = "http://localhost:"+port+"/cursos/del-disciplina";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("idCurso", 2)
                .queryParam("idDisciplina", 2);

        ResponseEntity response = restTemplate
                .exchange(builder.build().encode().toUri(),
                        HttpMethod.DELETE,
                        httpEntity,
                        Void.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
    }
}
