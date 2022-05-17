package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.Curso;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.models.Turno;
import com.sistema.universitario.repositories.CursoRepository;
import com.sistema.universitario.repositories.DisciplinaRepository;
import com.sistema.universitario.services.CursoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    Curso cursoTeste;
    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @BeforeEach
    void iniciaTestesEach(){
        cursoTeste = new Curso();
        cursoTeste.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        cursoTeste.setTurno(Turno.NOITE);

    }


    @Test
    @DisplayName("teste criar curso")
    void criarCurso(){
        var cursoSave = new Curso();
        cursoSave.setNomeCurso("Ciências da Computação");
        cursoSave.setTurno(Turno.NOITE);

        var cursoRetorno = new Curso();
        cursoRetorno.setId(1L);
        cursoRetorno.setNomeCurso("Ciências da Computação");
        cursoRetorno.setTurno(Turno.NOITE);

        when(cursoRepository.save(cursoSave)).thenReturn(cursoRetorno);

        cursoRetorno = cursoService.saveCurso(cursoSave);

        Assertions.assertNotNull(cursoRetorno);
        Assertions.assertNotNull(cursoRetorno.getId());
        Assertions.assertEquals(1L, cursoRetorno.getId());
        Assertions.assertEquals(cursoSave.getNomeCurso(), cursoRetorno.getNomeCurso());
        Assertions.assertEquals(cursoSave.getTurno(), cursoRetorno.getTurno());
    }
   /* @Test
    @DisplayName("verifica campo nulo na cricao de curso")
    void verificaCampoDeNomeNuloEmCriacaoDeCurso(){
       cursoTeste.setNomeCurso(null);

       try{
           var cursoSalvo = cursoService.saveCurso(cursoTeste);
           fail("Erro: não validou os parâmetros");
       } catch(Exception e){
           assertEquals("Nome do curso não informado!", e.getMessage());
       }

    }*/

    @Test
    @DisplayName("teste listar todos os cursos")
    void allCursos(){
        List<Curso> cursoList = new ArrayList<>();
        cursoList.add(
                new Curso(1L, "Ciência da Computação", Turno.MANHA, List.of(new Disciplina( "Estrutura de Dados"))));

        when(cursoRepository.findAll()).thenReturn(cursoList);

        List<Curso> cursoRetorno = cursoService.findAllCursos();
        Assertions.assertNotNull(cursoRetorno);
        Assertions.assertEquals(cursoList.size(), cursoRetorno.size());
    }

    @Test
    @DisplayName("teste buscar por id - curso")
    void buscarCursoPorId(){
        var curso = new Curso();
        curso.setId(1L);
        curso.setNomeCurso("Engenharia da Computação");
        curso.setTurno(Turno.MANHA);

        when(cursoRepository.findById(curso.getId()))
                .thenReturn(Optional.of(curso));

        var cursoRetorno = cursoService.findCursoById(curso.getId());

        Assertions.assertNotNull(cursoRetorno);
        Assertions.assertEquals(curso.getId(), cursoRetorno.getId());
    }

    @Test
    @DisplayName("verifica curso com id inexistente")
    void verificaIdNuloDeCurso(){
        cursoTeste.setId(1L);
        when(cursoRepository.findById(cursoTeste.getId())).thenReturn(Optional.empty());
        try {
            cursoService.findCursoById(cursoTeste.getId());
            fail("Deveria ter dado erro de id");
        } catch (Exception e){
            assertEquals("Curso não encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("teste atualizar curso")
    void atualizarCurso(){
        var oldCurso = new Curso();
        oldCurso.setId(1L);
        oldCurso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        oldCurso.setTurno(Turno.MANHA);

        var cursoAtual = new Curso();
        cursoAtual.setId(oldCurso.getId());
        cursoAtual.setNomeCurso("Sistemas de Informação");
        cursoAtual.setTurno(oldCurso.getTurno());

        when(cursoRepository.findById(oldCurso.getId())).thenReturn(Optional.of(oldCurso));
        when(cursoRepository.save(oldCurso)).thenReturn(cursoAtual);

        cursoAtual = cursoService.updateCurso(oldCurso.getId(), oldCurso);
        Assertions.assertNotNull(oldCurso);
        Assertions.assertNotNull(cursoAtual);
        Assertions.assertEquals(oldCurso.getId(), cursoAtual.getId());
    }

    @Test
    @DisplayName("teste exclusão de curso")
    void deletarCurso(){
        var curso = new Curso();
        curso.setId(1L);
        curso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        curso.setTurno(Turno.NOITE);

        when(cursoRepository.findById(curso.getId()))
                .thenReturn(Optional.of(curso));

        doNothing().when(cursoRepository).delete(curso);
        cursoService.deleteCurso(curso.getId());
        var cursoRetorno = cursoRepository.findById(curso.getId());

        Assertions.assertNotNull(cursoRetorno);
        verify(cursoRepository, times(1)).delete(curso);
    }

    @Test
    @DisplayName("teste incluir disciplina de curso")
    void salvarDisciplinaEmCurso(){
        var curso = new Curso();
        curso.setId(1L);
        curso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        curso.setTurno(Turno.NOITE);

        var disciplina = new Disciplina( "Estrutura de Dados");
        disciplina.setId(1L);

        var cursoAtualizado = new Curso();
        cursoAtualizado.setId(curso.getId());
        cursoAtualizado.setNomeCurso(curso.getNomeCurso());
        cursoAtualizado.setTurno(curso.getTurno());
        cursoAtualizado.getDisciplinas().add(disciplina);

        when(cursoRepository.findById(curso.getId())).thenReturn(Optional.of(curso));
        when(disciplinaRepository.findById(disciplina.getId())).thenReturn(Optional.of(disciplina));
        when(cursoRepository.save(curso)).thenReturn(cursoAtualizado);

        var cursoRetorno = cursoService.addCursoDisciplina(curso.getId(), disciplina.getId());

        Assertions.assertNotNull(cursoRetorno);
        Assertions.assertNotNull(cursoAtualizado);
        Assertions.assertEquals(cursoRetorno.getDisciplinas(), cursoRetorno.getDisciplinas());
    }

    @Test
    @DisplayName("verifica disciplina existente por id")
    void verifcaDisciplinaExistentePorId(){
        cursoTeste.setId(20L);
        var disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setNome("SIstemas Embarcados");

        when(disciplinaRepository.findById(disciplina.getId())).thenReturn(Optional.empty());
        when(cursoRepository.findById(cursoTeste.getId())).thenReturn(Optional.of(cursoTeste));
        try{
            cursoService.addCursoDisciplina(cursoTeste.getId(), disciplina.getId());
            fail("Não deveria salvar disciplina");
        } catch(Exception e){
            assertEquals("Disciplina não encontrada", e.getMessage());
        }
    }

    @Test
    @DisplayName("teste excluir disciplina de curso")
    void excluirDisciplinaCurso(){
        var curso = new Curso();
        curso.setId(1L);
        curso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
        curso.setTurno(Turno.NOITE);
        var disciplina = new Disciplina( "Estrutura de Dados");
        disciplina.setId(1L);
        curso.getDisciplinas().add(disciplina);

        var cursoAtualizado = new Curso();
        cursoAtualizado.setId(curso.getId());
        cursoAtualizado.setNomeCurso(curso.getNomeCurso());
        cursoAtualizado.setTurno(curso.getTurno());
        cursoAtualizado.getDisciplinas().add(disciplina);
        cursoAtualizado.getDisciplinas().remove(disciplina);

        when(cursoRepository.findById(curso.getId())).thenReturn(Optional.of(curso));

        when(disciplinaRepository.findById(disciplina.getId())).thenReturn(Optional.of(disciplina));
        when(cursoRepository.save(curso))
                .thenReturn(cursoAtualizado);

        var cursoRetorno = cursoService.deleteCursoDisciplina(curso.getId(), disciplina.getId());

        Assertions.assertNotNull(cursoRetorno);
        Assertions.assertNotNull(cursoAtualizado);
        verify(cursoRepository, times(1)).save(curso);
        Assertions.assertEquals(cursoAtualizado.getDisciplinas(), cursoRetorno.getDisciplinas());
    }
}
