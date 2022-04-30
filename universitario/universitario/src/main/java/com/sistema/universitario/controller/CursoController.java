package com.sistema.universitario.controller;

import com.sistema.universitario.models.Curso;
import com.sistema.universitario.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> getAllCursos(){
        return cursoService.findAllCursos();
    }

    @PostMapping
    public ResponseEntity saveCurso(@Valid @RequestBody Curso curso){
        this.cursoService.saveCurso(curso);
        return new ResponseEntity("Curso criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping({"id"})
    public ResponseEntity updateCurso(@PathVariable("id") Long idCurso, @RequestBody Curso curso){
        cursoService.updateCurso(idCurso, curso);
        return new ResponseEntity("Curso alterado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCurso(@PathVariable("id") Long id){
        cursoService.deleteCurso(id);
        return new ResponseEntity("Curso excluído com sucesso!", HttpStatus.OK);
    }


}
