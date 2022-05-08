package com.sistema.universitario.controller;

import com.sistema.universitario.models.Curso;
import com.sistema.universitario.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/todos-cursos")
    public List<Curso> getAllCursos(){
        return cursoService.findAllCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        var curso = this.cursoService.findCursoById(id);
        return new ResponseEntity(curso, HttpStatus.FOUND);
    }

    @PostMapping("/novo-curso")
    public ResponseEntity saveCurso(@Valid @RequestBody Curso curso){
        this.cursoService.saveCurso(curso);
        return new ResponseEntity("Curso criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateCurso(@PathVariable("id") Long idCurso, @RequestBody Curso curso){
        cursoService.updateCurso(idCurso, curso);
        return new ResponseEntity("Curso alterado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteCurso(@PathVariable("id") Long id){
        cursoService.deleteCurso(id);
        return new ResponseEntity("Curso excluído com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("del-disciplina")
    public ResponseEntity deleteCursoDisciplina(@RequestParam("idCurso") Long idCurso,
                                                    @RequestParam("idDisciplina") Long idDisciplina) {
        var curso = cursoService.deleteCursoDisciplina(idCurso, idDisciplina);
        return new ResponseEntity("Disciplina excluída do curso " + curso.getNomeCurso() + "com sucesso!", HttpStatus.OK);
    }

    @PostMapping("add-disciplina")
    public ResponseEntity addCursoDisciplina(@RequestParam("idCurso") Long idCurso,
                                                @RequestParam("idDisciplina") Long idDisciplina) {
        var curso = cursoService.addCursoDisciplina(idCurso, idDisciplina);
        return new ResponseEntity("Disciplina adiciona no curso " + curso.getNomeCurso() + "com sucesso!", HttpStatus.OK);
    }
}
