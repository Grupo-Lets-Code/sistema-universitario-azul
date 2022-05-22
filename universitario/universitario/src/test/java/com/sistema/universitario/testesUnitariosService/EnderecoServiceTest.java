package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.exceptions.endereco.*;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.repositories.EnderecoRepository;
import com.sistema.universitario.services.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;
    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    @DisplayName("Teste - Salvar novo endereco")
    void saveEndereco(){

        Endereco endereco = new Endereco();
        endereco.setCep("12352650");
        endereco.setCidade("São Paulo");
        endereco.setRua("Avenida Grande");
        endereco.setBairro("Bragança");
        endereco.setNum("1250");

        Endereco enderecoCadastrado = new Endereco();
        enderecoCadastrado.setId(1L);
        enderecoCadastrado.setCep("12352650");
        enderecoCadastrado.setCidade("São Paulo");
        enderecoCadastrado.setRua("Avenida Grande");
        enderecoCadastrado.setBairro("Bragança");
        enderecoCadastrado.setNum("1250");

        Mockito.when(enderecoRepository.save(endereco))
                .thenReturn(enderecoCadastrado);

        enderecoCadastrado = enderecoService.saveEndereco(endereco);

        Assertions.assertNotNull(enderecoCadastrado);
        Assertions.assertEquals(1L, enderecoCadastrado.getId());
        Assertions.assertEquals(endereco.getCep(), enderecoCadastrado.getCep());
        Assertions.assertEquals(endereco.getRua(), enderecoCadastrado.getRua());
        Assertions.assertEquals(endereco.getNum(), enderecoCadastrado.getNum());
        Assertions.assertEquals(endereco.getBairro(), enderecoCadastrado.getBairro());
        Assertions.assertEquals(endereco.getCidade(), enderecoCadastrado.getCidade());

    }

    @Test
    @DisplayName("Teste - Verificar endereço cadastrado")
    void getEndereco(){

        Endereco enderecoCadastrado = new Endereco();
        enderecoCadastrado.setId(1L);
        enderecoCadastrado.setCep("12352650");
        enderecoCadastrado.setCidade("São Paulo");
        enderecoCadastrado.setRua("Avenida Grande");
        enderecoCadastrado.setBairro("Bragança");
        enderecoCadastrado.setNum("1250");

        Mockito.when(enderecoRepository.findById(1L))
                .thenReturn(Optional.of(enderecoCadastrado));

        Endereco enderecoEncontrado = enderecoService.findEnderecoById(1L);

        Assertions.assertNotNull(enderecoEncontrado);
        Assertions.assertEquals(1L, enderecoEncontrado.getId());
        Assertions.assertEquals("12352650", enderecoEncontrado.getCep());
        Assertions.assertEquals("São Paulo", enderecoEncontrado.getCidade());
        Assertions.assertEquals("Avenida Grande", enderecoEncontrado.getRua());
        Assertions.assertEquals("Bragança", enderecoEncontrado.getBairro());
        Assertions.assertEquals("1250", enderecoEncontrado.getNum());

    }

    @Test
    @DisplayName("Teste - Listar todos os endereços")
    void getAllEnderecos(){

        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setCep("12352650");
        endereco1.setCidade("São Paulo");
        endereco1.setRua("Avenida Grande");
        endereco1.setBairro("Bragança");
        endereco1.setNum("1250");

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setCep("12345678");
        endereco2.setCidade("Rio de Janeiro");
        endereco2.setRua("Rua Ipanema");
        endereco2.setBairro("Alto Colorado");
        endereco2.setNum("100");

        Endereco endereco3 = new Endereco();
        endereco3.setId(3L);
        endereco3.setCep("12777665");
        endereco3.setCidade("São Paulo");
        endereco3.setRua("Avenida Pequena");
        endereco3.setBairro("Paulista");
        endereco3.setNum("152");

        List<Endereco> enderecoList = new ArrayList<>();
        enderecoList.add(endereco1);
        enderecoList.add(endereco2);
        enderecoList.add(endereco3);

        Mockito.when(enderecoRepository.findAll())
                .thenReturn(enderecoList);

        List<Endereco> enderecos = enderecoService.listAll();

        Assertions.assertNotNull(enderecos);
        Assertions.assertFalse(enderecos.isEmpty());
        Assertions.assertEquals(3, enderecos.size());
        Assertions.assertEquals(enderecos.get(0), enderecoList.get(0));
        Assertions.assertEquals(enderecos.get(1), enderecoList.get(1));
        Assertions.assertEquals(enderecos.get(2), enderecoList.get(2));
    }

    @Test
    @DisplayName("Teste - Atualizar endereço cadastrado")
    void updateEndereco(){

        Endereco enderecoAntigo = new Endereco();
        enderecoAntigo.setId(1L);
        enderecoAntigo.setCep("12352650");
        enderecoAntigo.setCidade("São Paulo");
        enderecoAntigo.setRua("Avenida Grande");
        enderecoAntigo.setBairro("Bragança");
        enderecoAntigo.setNum("1250");

        Endereco enderecoNovo = new Endereco();
        enderecoNovo.setId(1L);
        enderecoNovo.setCep("12777665");
        enderecoNovo.setCidade("São Paulo");
        enderecoNovo.setRua("Avenida Pequena");
        enderecoNovo.setBairro("Paulista");
        enderecoNovo.setNum("152");

        Mockito.when(enderecoRepository.findById(enderecoAntigo.getId()))
                .thenReturn(Optional.of(enderecoAntigo));

        enderecoService.updateEndereco(enderecoAntigo.getId(), enderecoNovo);

        Assertions.assertNotNull(enderecoAntigo);
        Assertions.assertEquals(1L, enderecoAntigo.getId());
        Assertions.assertEquals("12777665", enderecoAntigo.getCep());
        Assertions.assertEquals("São Paulo", enderecoAntigo.getCidade());
        Assertions.assertEquals("Avenida Pequena", enderecoAntigo.getRua());
        Assertions.assertEquals("Paulista", enderecoAntigo.getBairro());
        Assertions.assertEquals("152", enderecoAntigo.getNum());

    }

    @Test
    @DisplayName("Teste - Deletar Endereço")
    void deleteEndereco(){

        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setCep("12352650");
        endereco1.setCidade("São Paulo");
        endereco1.setRua("Avenida Grande");
        endereco1.setBairro("Bragança");
        endereco1.setNum("1250");

        Mockito.when(enderecoRepository.findById(1L))
                .thenReturn(Optional.of(endereco1));

        enderecoService.deleteEndereco(1L);

        Mockito.verify(enderecoRepository).delete(endereco1);

        Assertions.assertNotNull(endereco1);
        Assertions.assertEquals("12352650", endereco1.getCep());
        Assertions.assertEquals("São Paulo", endereco1.getCidade());
        Assertions.assertEquals("Avenida Grande", endereco1.getRua());
        Assertions.assertEquals("Bragança", endereco1.getBairro());
        Assertions.assertEquals("1250", endereco1.getNum());

    }

    @Test
    @DisplayName("Teste - EnderecoJaCadastradoException")
    void enderecoJaCadastradoException(){

        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setCep("12352650");
        endereco1.setCidade("São Paulo");
        endereco1.setRua("Avenida Grande");
        endereco1.setBairro("Bragança");
        endereco1.setNum("1250");

        Mockito.when(enderecoRepository.existsById(endereco1.getId()))
                .thenReturn(true);

        try{
            enderecoService.saveEndereco(endereco1);
            Assertions.fail("Deveria dar EnderecoJaCadastradoException");
        } catch (EnderecoJaCadastradoException e) {
            Assertions.assertEquals("Endereco ja cadastrado no sistema", e.getMessage());
        }

    }

    @Test
    @DisplayName("Teste - EnderecoInexistenteException")
    void enderecoInexistenteException(){

        Mockito.when(enderecoRepository.findById(15L))
                .thenReturn(Optional.empty());

        try{
            enderecoService.findEnderecoById(15L);
            Assertions.fail("Deveria dar EnderecoInexistenteException");
        } catch(EnderecoInexistenteException e){
            Assertions.assertEquals("Endereco nao encontrado", e.getMessage());
        }

    }

}