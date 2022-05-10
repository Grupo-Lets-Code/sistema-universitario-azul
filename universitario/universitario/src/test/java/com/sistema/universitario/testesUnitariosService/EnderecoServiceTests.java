package com.sistema.universitario.testesUnitariosService;

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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTests {

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

        Mockito.when(enderecoRepository.save(enderecoNovo))
                .thenReturn(enderecoNovo);

        enderecoAntigo = enderecoService.saveEndereco(enderecoNovo);

        Assertions.assertNotNull(enderecoAntigo);
        Assertions.assertEquals(1L, enderecoAntigo.getId());
        Assertions.assertEquals("12777665", enderecoAntigo.getCep());
        Assertions.assertEquals("São Paulo", enderecoAntigo.getCidade());
        Assertions.assertEquals("Avenida Pequena", enderecoAntigo.getRua());
        Assertions.assertEquals("Paulista", enderecoAntigo.getBairro());
        Assertions.assertEquals("152", enderecoAntigo.getNum());

    }

}