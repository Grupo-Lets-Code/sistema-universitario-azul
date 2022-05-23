package com.sistema.universitario.testesIntegracaoController;

import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.services.EnderecoService;
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
public class EnderecoIntegracaoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EnderecoService enderecoService;

    @Test
    void salvarEnderecoControllerIntTest(){

        Endereco endereco = new Endereco();
        Usuario usuario = new Usuario();

        usuario.setEmail("teste2@teste.com");
        usuario.setPassword("123456");

        usuarioService.save(usuario);

        endereco.setId(11L);
        endereco.setCep("12352650");
        endereco.setCidade("Sao Paulo");
        endereco.setRua("Avenida Grande");
        endereco.setBairro("Braganca");
        endereco.setNum("1250");
        endereco.setUsuario(usuario);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(endereco, httpHeaders);

        ResponseEntity<Void> response = this.restTemplate
                .exchange("/endereco", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    void listarTodosOsEnderecos(){

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Endereco[]> response = this.restTemplate
                .exchange("/endereco", HttpMethod.GET, httpEntity, Endereco[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotEquals(0, response.getBody().length);
        Assertions.assertNotNull(response.getBody()[0].getId());
        Assertions.assertNotNull(response.getBody()[1].getId());
        Assertions.assertNotNull(response.getBody()[2].getId());
        Assertions.assertNotNull(response.getBody()[3].getId());
        Assertions.assertNotNull(response.getBody()[4].getId());
        Assertions.assertNotNull(response.getBody()[5].getId());
        Assertions.assertNotNull(response.getBody()[6].getId());
        Assertions.assertNotNull(response.getBody()[7].getId());
        Assertions.assertNotNull(response.getBody()[8].getId());
        Assertions.assertNotNull(response.getBody()[9].getId());

    }

    @Test
    void pesquisarEnderecoById(){

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Endereco> response = this.restTemplate
                .exchange("/endereco/1", HttpMethod.GET, httpEntity, Endereco.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertNotNull(response.getBody().getCep());
        Assertions.assertNotNull(response.getBody().getNum());
        Assertions.assertNotNull(response.getBody().getRua());
        Assertions.assertNotNull(response.getBody().getBairro());
        Assertions.assertNotNull(response.getBody().getCidade());
        Assertions.assertNotNull(response.getBody().getUsuario());
    }

    @Test
    void atualizarEndereco(){

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("12352789");
        endereco.setCidade("Mato Grosso");
        endereco.setRua("Avenida Grande");
        endereco.setBairro("Capivari");
        endereco.setNum("150");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(endereco, httpHeaders);

        ResponseEntity<Void> response = this.restTemplate
                .exchange("/endereco/1", HttpMethod.PUT, httpEntity, Void.class);

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void deletarEndereco(){

        //O processo de delete apenas funciona com o ID 10 e os ids criados

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> response = this.restTemplate
                .exchange("/endereco/10", HttpMethod.DELETE, httpEntity, Void.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void testarEnderecoInexistenteException(){

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Endereco> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = this.restTemplate
                .exchange("/endereco/15", HttpMethod.GET, httpEntity, String.class);

        Assertions.assertEquals(404, response.getStatusCodeValue());

    }

}

