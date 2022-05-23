package com.sistema.universitario.testesIntegracaoService;

import com.sistema.universitario.exceptions.endereco.EnderecoInexistenteException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.services.EnderecoService;
import com.sistema.universitario.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@WebAppConfiguration
public class EnderecoIntegracaoServiceTest {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    UsuarioService usuarioService;

    @Test
    @Transactional
    void saveEnderecoIntegTest(){

        Endereco endereco = new Endereco();
        Usuario usuario = new Usuario();

        usuario.setEmail("teste4@teste.com");
        usuario.setPassword("123457");

        usuarioService.save(usuario);

        endereco.setCep("12352651");
        endereco.setCidade("Sao Paulo");
        endereco.setRua("Avenida Grande");
        endereco.setBairro("Braganca");
        endereco.setNum("120");
        endereco.setUsuario(usuario);

        enderecoService.saveEndereco(endereco);

        List<Endereco> enderecoList = enderecoService.listAll();

        for (Endereco e : enderecoList) {
            System.out.println(e.getId());
        }

        Assertions.assertNotNull(enderecoService.findEnderecoById(11L));
        Assertions.assertEquals(endereco.getId(), enderecoService.findEnderecoById(11L).getId());
        Assertions.assertEquals(endereco.getCep(), enderecoService.findEnderecoById(11L).getCep());
        Assertions.assertEquals(endereco.getCidade(), enderecoService.findEnderecoById(11L).getCidade());
        Assertions.assertEquals(endereco.getRua(), enderecoService.findEnderecoById(11L).getRua());
        Assertions.assertEquals(endereco.getBairro(), enderecoService.findEnderecoById(11L).getBairro());
        Assertions.assertEquals(endereco.getNum(), enderecoService.findEnderecoById(11L).getNum());
        Assertions.assertEquals(endereco.getUsuario(), enderecoService.findEnderecoById(11L).getUsuario());

    }

    @Test
    @Transactional
    void getEnderecoByIdIntegTest(){

        Endereco enderecoExistente = enderecoService.findEnderecoById(2L);

        Assertions.assertNotNull(enderecoExistente);
        Assertions.assertEquals(2L, enderecoExistente.getId());
        Assertions.assertEquals("12345678", enderecoExistente.getCep());
        Assertions.assertEquals("Pindamonhangaba", enderecoExistente.getCidade());
        Assertions.assertEquals("Rua Teste 2", enderecoExistente.getRua());
        Assertions.assertEquals("Bairro Teste 2", enderecoExistente.getBairro());
        Assertions.assertEquals("02", enderecoExistente.getNum());

    }

    @Test
    @Transactional
    void getAllEnderecoIntegTest(){

        List<Endereco> enderecoList = enderecoService.listAll();

        Assertions.assertNotNull(enderecoList);
        Assertions.assertEquals(10, enderecoList.size());
    }

    @Test
    @Transactional
    void updateEnderecoIntegTest(){

        Endereco enderecoAntigo = enderecoService.findEnderecoById(1L);

        Endereco enderecoNovo = new Endereco();
        enderecoNovo.setId(1L);
        enderecoNovo.setCep("12352650");
        enderecoNovo.setCidade("São Paulo");
        enderecoNovo.setRua("Avenida Grande");
        enderecoNovo.setBairro("Bragança");
        enderecoNovo.setNum("1250");

        enderecoService.updateEndereco(1L, enderecoNovo);

        Assertions.assertNotNull(enderecoAntigo);
        Assertions.assertEquals(enderecoNovo.getCep(), enderecoAntigo.getCep());
        Assertions.assertEquals(enderecoNovo.getCidade(), enderecoAntigo.getCidade());
        Assertions.assertEquals(enderecoNovo.getRua(), enderecoAntigo.getRua());
        Assertions.assertEquals(enderecoNovo.getBairro(), enderecoAntigo.getBairro());
        Assertions.assertEquals(enderecoNovo.getNum(), enderecoAntigo.getNum());

    }

    @Test
    @Transactional
    void deleteEnderecoIntegTest(){

        //O processo de delete apenas funciona com o ID 10 e os ids criados

        List<Endereco> enderecosIniciais = enderecoService.listAll();

        enderecoService.deleteEndereco(10L);

        List<Endereco> enderecosFinais = enderecoService.listAll();

        Assertions.assertNotEquals(enderecosFinais, enderecosIniciais);
        Assertions.assertEquals(9, enderecosFinais.size());

    }

    @Test
    @Transactional
    void EnderecoInexistenteExceptionIntegTest(){

        try{
            enderecoService.deleteEndereco(10L);
            enderecoService.findEnderecoById(10L);
            Assertions.fail("Deveria dar a exceção EndereçoInexistenteException");
        } catch (EnderecoInexistenteException e){
            Assertions.assertEquals("Endereco nao encontrado", e.getMessage());
        }

    }

}