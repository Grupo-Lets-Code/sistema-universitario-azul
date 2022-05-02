package com.sistema.universitario.controller;

import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.repositories.AlunoRepository;
import com.sistema.universitario.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping ("/aluno")
public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService, AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
    }

    @GetMapping("todos-alunos")
    public List<Aluno> getAll(){
        return this.alunoRepository.findAll();
    }

    @GetMapping
    public String index(Model model){
        List<Aluno> alunos = getAll();
        model.addAttribute("alunos", alunos);
        return "index";
    }

    @GetMapping("/aluno-ativo")
    public List<Aluno> getAllAlunosAtivos(){
        return this.alunoRepository.findByAlunosAtivos("ATIVO");
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Aluno aluno){
        this.alunoRepository.save(aluno);
        return new ResponseEntity("Aluno criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
            alunoService.update(id, aluno);
            return new ResponseEntity("Aluno Atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("deletar-aluno/{idAluno}")
    public ResponseEntity deleteAlunoTurma(@PathVariable("idAluno") Long idAluno) {
        alunoService.deleteAlunoStatus(idAluno);
        return new ResponseEntity("Aluno(a) excluído(a) da turma com sucesso!", HttpStatus.OK);
    }
}
