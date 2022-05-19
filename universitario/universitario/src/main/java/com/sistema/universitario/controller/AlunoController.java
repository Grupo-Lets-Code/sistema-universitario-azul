package com.sistema.universitario.controller;

import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Professor;
import com.sistema.universitario.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping ("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/todos-alunos")
    public List<Aluno> getAll(){
        return this.alunoService.findAll();
    }

    @GetMapping("/aluno-ativo")
    public List<Aluno> getAllAlunosAtivos() {
        return this.alunoService.findAllAtivos("ATIVO");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity save(@Valid @RequestBody Aluno aluno) {
        this.alunoService.save(aluno);
        return new ResponseEntity("Aluno(a) criado(a) com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
            alunoService.update(id, aluno);
            return new ResponseEntity("Aluno(a) atualizado(a) com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("deletar-aluno/{idAluno}")
    public ResponseEntity deleteAluno(@PathVariable("idAluno") Long idAluno) {
        alunoService.deleteAlunoStatus(idAluno);
        return new ResponseEntity("Aluno(a) excluído(a) com sucesso!", HttpStatus.OK);
    }
}
