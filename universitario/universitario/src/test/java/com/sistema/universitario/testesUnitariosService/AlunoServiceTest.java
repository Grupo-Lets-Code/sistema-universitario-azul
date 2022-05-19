package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.AlunoRepository;
import com.sistema.universitario.services.AlunoService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;
    @Mock
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Teste listar todos - Alunos")
    void findALlAlunos() {
        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(
                new Aluno(1L, (new Usuario()), "Aluno Teste",
                        "00000099910", (new Endereco()), StatusUsuario.ATIVO));

        when(alunoRepository.findAll())
                .thenReturn(alunoList);

        List<Aluno> alunos = alunoService.findAll();
        Assertions.assertNotNull(alunos);
        Assertions.assertFalse(alunos.isEmpty());
        Assertions.assertEquals(1, alunoList.size());
    }

    @Test
    @DisplayName("Teste cadastrar - Aluno")
    void saveAluno() {
        Aluno alunoSave = new Aluno();
        alunoSave.setNome("Catrina");
        alunoSave.setCpf("12345678901");
        alunoSave.setStatus(StatusUsuario.ATIVO);

        Aluno alunoReturn = new Aluno();
        alunoReturn.setId(42L);
        alunoReturn.setNome("Catrina");
        alunoReturn.setCpf("12345678901");
        alunoReturn.setStatus(StatusUsuario.ATIVO);

        when(alunoRepository.save(alunoSave))
                .thenReturn(alunoReturn);

        alunoReturn = alunoService.save(alunoSave);

        Assertions.assertNotNull(alunoReturn);
        Assertions.assertEquals(alunoSave.getNome(), alunoReturn.getNome());
        Assertions.assertEquals(alunoSave.getCpf(), alunoReturn.getCpf());
        Assertions.assertEquals(StatusUsuario.ATIVO, alunoReturn.getStatus());
    }

    @Test
    @DisplayName("Teste deletar - Aluno")
    void deleteAlunoStatus() {
        Aluno aluno = new Aluno();
        aluno.setId(2L);
        aluno.setNome("JOAO");
        aluno.setCpf("33345678901");
        aluno.setStatus(StatusUsuario.ATIVO);

        when(alunoRepository.findById((long) 2))
                .thenReturn(Optional.of(aluno));

        alunoService.deleteAlunoStatus((long) 2);

        Optional<Aluno> alunoInativo = alunoRepository.findById((long)2);
        assertThat(alunoInativo);
    }

    @Test
    @DisplayName("Teste atualizar - Aluno")
    void updateAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Marcos");

        Aluno alunoNovo = new Aluno();

        alunoNovo.setNome("Joao");

        when(alunoRepository.findById((long)1))
                .thenReturn(Optional.of(aluno));

        alunoService.update((long) 1 , alunoNovo);

        verify(alunoRepository).save(aluno);

        verify(alunoRepository).findById((long) 1);

    }
    @Test
    void validaRetornoUsuariosAtivos(){
        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(
                new Aluno(1L, (new Usuario()), "Aluno Teste",
                        "00000099910", (new Endereco()), StatusUsuario.ATIVO));
        alunoList.add(
                new Aluno(2L, (new Usuario()), "Aluno Teste 2",
                "11111111111", (new Endereco()), StatusUsuario.ATIVO));
        alunoList.add(
                new Aluno(3L, (new Usuario()), "Aluno Teste 3",
                "222222222222", (new Endereco()), StatusUsuario.INATIVO));

        Aluno indice = new Aluno();

        for (Aluno aluno:alunoList){
            if (aluno.getStatus() == StatusUsuario.INATIVO){
                indice = aluno;
            }
        }
        alunoList.remove(indice);

        when(alunoRepository.findBy("ATIVO"))
                .thenReturn(alunoList);

        var alunoListRetorno = alunoService.findAllAtivos("ATIVO");

        Assertions.assertNotNull(alunoListRetorno);
        Assertions.assertEquals(alunoList, alunoListRetorno);
        Assertions.assertEquals(2, alunoListRetorno.size());

    }
}
