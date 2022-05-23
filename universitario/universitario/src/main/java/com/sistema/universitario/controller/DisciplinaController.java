package com.sistema.universitario.controller;

import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.services.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/listar-todas")
    public List<Disciplina> getAll() {
        return disciplinaService.findAll();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity save(@Valid @RequestBody Disciplina disciplina) {
        this.disciplinaService.save(disciplina);
        return new ResponseEntity("Disciplina criada com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping({"/atualizar/{id}"})
    public ResponseEntity update(@PathVariable("id") Long idDisciplina,
                                           @RequestBody Disciplina disciplina) {
        disciplinaService.update(idDisciplina, disciplina);
        return new ResponseEntity("Disciplina alterada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        disciplinaService.delete(id);
        return new ResponseEntity("Disciplina excluída com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/deletar-professor/{idProfessor}/{idDisciplina}")
    public ResponseEntity deleteProfessorDisciplina(@PathVariable("idProfessor") Long idProfessor,
                                                    @PathVariable("idDisciplina") Long idDisciplina) {
        disciplinaService.deleteProfessorDisciplina(idProfessor, idDisciplina);
        return new ResponseEntity("Professor(a) excluído(a) da disciplina com sucesso!", HttpStatus.OK);
    }
}
