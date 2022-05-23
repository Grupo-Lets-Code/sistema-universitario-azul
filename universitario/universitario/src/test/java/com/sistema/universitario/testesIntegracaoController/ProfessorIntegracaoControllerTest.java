package com.sistema.universitario.testesIntegracaoController;

import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.services.EnderecoService;
import com.sistema.universitario.services.ProfessorService;
import com.sistema.universitario.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessorIntegracaoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ProfessorService professorService;

    @Test
    void listarTodosProfessores() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Professor[]> response = this.restTemplate
                .exchange("/professor/listar-todos", HttpMethod.GET, httpEntity, Professor[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody()[0].getNome());
        Assertions.assertNotNull(response.getBody()[0].getCpf());
    }

    @Test
    void listarProfessoresAtivos() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Professor[]> response = this.restTemplate
                .exchange("/professor/listar-ativos", HttpMethod.GET, httpEntity, Professor[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(StatusUsuario.ATIVO, response.getBody()[0].getStatus());
    }

    @Test
    void encontrarProfessorPorId() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/professor/encontrar/1", HttpMethod.GET, httpEntity, String.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void cadastrarProfessor() {
        var catchUserId = usuarioService.findById(1L);
        var catchEndId = enderecoService.findEnderecoById(1L);

        Professor professor = new Professor();
        professor.setUsuario(catchUserId);
        professor.setNome("Integracao Controller");
        professor.setCpf("123456");
        professor.setEndereco(catchEndId);
        professor.setStatus(StatusUsuario.ATIVO);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(professor, httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/professor/cadastrar", HttpMethod.POST, httpEntity, String.class);

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void atualizarProfessor() {
        var professor = professorService.findById(1L);
        professor.setNome("Integracao Controller Atualizado");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(professor, httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/professor/atualizar/1", HttpMethod.PUT, httpEntity, String.class);

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deletarProfessor() {
        var professor = professorService.findById(1L);
        professor.setStatus(StatusUsuario.INATIVO);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(professor, httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/professor/deletar/1", HttpMethod.DELETE, httpEntity, String.class);

        Assertions.assertNotNull(professor);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }
}