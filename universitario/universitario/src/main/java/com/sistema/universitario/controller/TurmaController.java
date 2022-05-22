package com.sistema.universitario.controller;

import com.sistema.universitario.models.Turma;
import com.sistema.universitario.services.TurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final Logger LOGGER = LoggerFactory.getLogger(TurmaController.class);
    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public List<Turma> getAllTurmas() {
        return this.turmaService.findAllTurmas();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTurmasById(@PathVariable("id") Long id){
        var turma = this.turmaService.findTurmaById(id);
        return new ResponseEntity(turma, HttpStatus.FOUND);
    }

    @PostMapping("/salvar-turma")
    public ResponseEntity saveTurma(@RequestBody Turma turma) {
        turmaService.saveTurma(turma);
        return new ResponseEntity("Turma criada com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/update-turma/{id}")
    public ResponseEntity updateNomeTurma(@PathVariable("id") Long idTurma, @RequestBody String nomeTurma) {
        Turma turma = turmaService.updateNomeTurma(idTurma, nomeTurma);
        return new ResponseEntity("Nome da turma alterado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/remove-turma/{id}")
    public ResponseEntity deleteTurma(@PathVariable Long id) {
        Turma turma = turmaService.deleteTurma(id);
        return new ResponseEntity(turma, HttpStatus.OK);
    }

    @PostMapping("/add-turma-aluno/{idTurma}/{idAluno}")
    public ResponseEntity adicionaTurmaAluno(@PathVariable("idTurma") Long idTurma,
                                             @PathVariable("idAluno") Long idAluno) {
        turmaService.addTurmaAluno(idTurma, idAluno);
        return new ResponseEntity("Aluno inserido na turma com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/remove-turma-aluno/{idTurma}/{idAluno}")
    public ResponseEntity removeTurmaAluno(@PathVariable("idTurma") Long idTurma,
                                           @PathVariable("idAluno") Long idAluno) {
        turmaService.removeTurmaAluno(idTurma, idAluno);
        return new ResponseEntity("Aluno removido da turma com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/add-turma-professor/{idTurma}/{idProfessor}")
    public ResponseEntity adicionaTurmaProfessor(@PathVariable("idTurma") Long idTurma,
                                             @PathVariable("idProfessor") Long idProfessor) {
        turmaService.addTurmaProfessor(idTurma, idProfessor);
        return new ResponseEntity("Professor inserido na turma com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/remove-turma-professor/{idTurma}/{idProfessor}")
    public ResponseEntity removeTurmaProfessor(@PathVariable("idTurma") Long idTurma,
                                           @PathVariable("idProfessor") Long idProfessor) {
        turmaService.removeTurmaProfessor(idTurma, idProfessor);
        return new ResponseEntity("Professor removido da turma com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/add-turma-disciplina/{idTurma}/{idDisciplina}")
    public ResponseEntity adicionaTurmaDisciplina(@PathVariable("idTurma") Long idTurma,
                                             @PathVariable("idDisciplina") Long idDisciplina) {
        turmaService.addTurmaDisciplina(idTurma, idDisciplina);
        return new ResponseEntity("Disciplina inserida na turma com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/remove-turma-disciplina/{idTurma}/{idDisciplina}")
    public ResponseEntity removeTurmaDisciplina(@PathVariable("idTurma") Long idTurma,
                                           @PathVariable("idDisciplina") Long idDisciplina) {
        turmaService.removeTurmaDisciplina(idTurma, idDisciplina);
        return new ResponseEntity("Disciplina removida da turma com sucesso!", HttpStatus.OK);
    }

}