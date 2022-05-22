package com.sistema.universitario.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.universitario.controller.EnderecoController;
import com.sistema.universitario.exceptions.endereco.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.endereco.EnderecoJaCadastradoException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.services.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EnderecoController.class)
public class EnderecoControllerTest {

    @MockBean
    private EnderecoService enderecoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void selecionaEnderecoByIdTest() throws Exception{

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

        Mockito.when(enderecoService.findEnderecoById(2L))
                .thenReturn(enderecoList.get(1));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/endereco/2")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void listaEnderecosExistentesTest() throws Exception {

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

        Mockito.when(enderecoService.listAll())
                .thenReturn(enderecoList);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/endereco")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void salvarEnderecoTest() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setCep("12345678");
        endereco.setCidade("São Bento do Sapucaí");
        endereco.setRua("Avenida Alta");
        endereco.setBairro("Bragança");
        endereco.setNum("202");

        Mockito.when(enderecoService.saveEndereco(endereco))
                .thenReturn(endereco);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/endereco")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(endereco))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Assertions.assertEquals("12345678", endereco.getCep());
        Assertions.assertEquals("São Bento do Sapucaí", endereco.getCidade());
        Assertions.assertEquals("Avenida Alta", endereco.getRua());
        Assertions.assertEquals("Bragança", endereco.getBairro());
        Assertions.assertEquals("202", endereco.getNum());

    }

    @Test
    void atualizarEnderecoTest() throws Exception {

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

        Mockito.when(enderecoService.findEnderecoById(enderecoAntigo.getId()))
                .thenReturn(enderecoAntigo);

        Mockito.when(enderecoService.updateEndereco(enderecoAntigo.getId(), enderecoNovo))
                .thenReturn(enderecoNovo);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/endereco/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(enderecoNovo))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Endereco atualizado com sucesso"));

    }

    @Test
    void deletarEnderecoTest() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("12777665");
        endereco.setCidade("São Paulo");
        endereco.setRua("Avenida Pequena");
        endereco.setBairro("Paulista");
        endereco.setNum("152");

        Mockito.when(enderecoService.findEnderecoById(endereco.getId()))
                .thenReturn(endereco);

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/endereco/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(endereco))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Endereco deletado com sucesso"));

    }

    @Test
    void testEnderecoNaoEncontradoException() throws Exception{

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setCep("12777665");
        endereco.setCidade("São Paulo");
        endereco.setRua("Avenida Pequena");
        endereco.setBairro("Paulista");
        endereco.setNum("152");

        Mockito.when(enderecoService.saveEndereco(endereco))
                .thenThrow(EnderecoJaCadastradoException.class);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/endereco")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(endereco))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof EnderecoJaCadastradoException));
    }

    @Test
    void testEnderecoInexistenteException() throws Exception{

        Mockito.when(enderecoService.findEnderecoById(15L))
                .thenThrow(EnderecoInexistenteException.class);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/endereco/15")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof EnderecoInexistenteException));

    }

}
