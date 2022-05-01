package com.sistema.universitario.controller;

import com.sistema.universitario.models.Professor;
import com.sistema.universitario.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> getAll() {
        return professorService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity getProfessorById(@PathVariable("id") Long id) {
        Professor professor = this.professorService.findById(id);
        return new ResponseEntity(professor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Professor professor) {
        this.professorService.save(professor);
        return new ResponseEntity("Professor(a) criado(a) com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity update(@PathVariable("id") Long idProfessor,
                                           @RequestBody Professor professor) {
        professorService.update(idProfessor, professor);
        return new ResponseEntity("Professor(a) alterado(a) com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        professorService.delete(id);
        return new ResponseEntity("Professor(a) excluído(a) com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("deletar-professor-disciplina/{idProfessor}/{idDisciplina}")
    public ResponseEntity deleteProfessorDisciplina(@PathVariable("idProfessor") Long idProfessor,
                                                    @PathVariable("idDisciplina") Long idDisciplina) {
        professorService.deleteProfessorDisciplina(idProfessor, idDisciplina);
        return new ResponseEntity("Professor(a) excluído(a) da disciplina com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("deletar-professor-turma/{idProfessor}/{idTurma}")
    public ResponseEntity deleteProfessorTurma(@PathVariable("idProfessor") Long idProfessor,
                                               @PathVariable("idTurma") Long idTurma) {
        professorService.deleteProfessorTurma(idProfessor, idTurma);
        return new ResponseEntity("Professor(a) excluído(a) da turma com sucesso!", HttpStatus.OK);
    }
}
