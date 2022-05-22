package com.sistema.universitario.testesIntegracaoService;

import com.sistema.universitario.models.Curso;
import com.sistema.universitario.models.Turno;
import com.sistema.universitario.services.CursoService;
import com.sistema.universitario.services.DisciplinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoIntegracaoServiceTest {

    @Autowired
    CursoService cursoService;

    @Autowired
    DisciplinaService disciplinaService;

    Curso cursoTest;

    @BeforeEach
    void iniciaTestesEach(){
        cursoTest = new Curso();
        cursoTest.setNomeCurso("Sistemas de Informação");
        cursoTest.setTurno(Turno.NOITE);
    }

    @Test
    @Transactional
    void salvarCursoTesteInteg(){
        var cursoSalvo = cursoService.saveCurso(cursoTest);

        assertNotNull(cursoSalvo.getId());
        assertEquals(cursoTest.getNomeCurso(), cursoSalvo.getNomeCurso());

    }

    @Test
    void nomeCursoObrigatorioTeste(){
        cursoTest.setNomeCurso(null);
        try{
            cursoService.saveCurso(cursoTest);
            fail("Deveria retornar um erro");
        } catch (Exception e){
            assertTrue(true);
            assertTrue(e.getMessage().contains("Nome do curso não informado!"));
        }
    }

    @Test
    void listaTodosOsCursosTestIntegracao(){
        List<Curso> cursoList = cursoService.findAllCursos();

        assertNotNull(cursoList);
        assertTrue(cursoList.size() > 0);
    }

    @Test
    @Transactional
    void salvarCursoEListarTodosOsCursosTest(){
        var cursoSalvo = cursoService.saveCurso(cursoTest);

        List<Curso> cursoList = cursoService.findAllCursos();

        assertTrue(cursoList.contains(cursoSalvo));

        AtomicReference<Curso> cursoRetornoList = new AtomicReference<>();
        cursoList.forEach(curso -> {
            if(curso.equals(cursoSalvo)){
                cursoRetornoList.set(curso);
            }
        });

        assertNotNull(cursoRetornoList.get());
        assertEquals(cursoSalvo.getNomeCurso(), cursoRetornoList.get().getNomeCurso());
    }

    @Test @Transactional
    void buscarCursoPorId(){
        var curso = cursoService.findCursoById(2L);

        assertNotNull(curso);
        assertNotNull(curso.getNomeCurso());
    }
    @Test @Transactional
    void deveRetornarErroAoBuscarCurso(){

        try{
           cursoService.findCursoById(100L);
            fail("Deveria dar erro!");
        } catch (Exception e) {
            assertTrue(true);
            assertTrue(e.getMessage().contains("Curso não encontrado"));
        }
    }

    @Test @Transactional
    void deveAtualizarDadosDeUmCurso(){
        var curso = cursoService.findCursoById(2L);
        var nomeCursoAntigo = curso.getNomeCurso();
        var turnoCursoAntigo = curso.getTurno();

        curso.setNomeCurso("Engenharia da Computação");
        curso.setTurno(Turno.MANHA);

        cursoService.updateCurso(2L, curso);

        var cursoAtualizado = cursoService.findCursoById(2L);

        assertNotNull(curso);
        assertNotNull(cursoAtualizado);
        assertNotEquals(nomeCursoAntigo, cursoAtualizado.getNomeCurso());
        assertNotEquals(turnoCursoAntigo, cursoAtualizado.getTurno());
    }

    @Test @Transactional
    void excluirCursoTesteIntegracao(){
        var curso = cursoService.findCursoById(2L);

        assertNotNull(curso);

        cursoService.deleteCurso(curso.getId());

       try{
         cursoService.findCursoById(curso.getId());
         fail("Deveria ter excluído o curso");
       } catch (Exception e){
           assertTrue(true);
           assertTrue(e.getMessage().contains("Curso não encontrado"));
       }
    }

    @Test @Transactional
    void testExceptionParaExclusaoDeCursoNaoExistente(){
        try{
            cursoService.deleteCurso(100L);
            fail("Erro! Curso não existe");
        } catch (Exception e){
            assertTrue(true);
            assertTrue(e.getMessage().contains("Curso não encontrado"));
        }
    }
    @Test @Transactional
    void testeInclusaoDeDisciplinaEmCurso(){
        var disciplina = disciplinaService.findById(3L);
        var curso = cursoService.findCursoById(2L);
        assertNotNull(disciplina);
        assertNotNull(curso);
        cursoService.addCursoDisciplina(curso.getId(), disciplina.getId());

        var cursoAtualizado = cursoService.findCursoById(curso.getId());

        assertNotNull(cursoAtualizado);
        assertTrue(cursoAtualizado.getDisciplinas().contains(disciplina));
    }

    @Test @Transactional
    void testeExclusaoDeDisciplinaEmCurso(){
        var disciplina = disciplinaService.findById(3L);
        var curso = cursoService.findCursoById(2L);
        assertNotNull(disciplina);
        assertNotNull(curso);

        var cursoRetorno = cursoService.addCursoDisciplina(curso.getId(), disciplina.getId());
        assertTrue(cursoRetorno.getDisciplinas().contains(disciplina));

        var cursoAtualizado = cursoService.deleteCursoDisciplina(curso.getId(), disciplina.getId());
        assertNotNull(cursoAtualizado);
        assertFalse(cursoAtualizado.getDisciplinas().contains(disciplina));
    }

}
