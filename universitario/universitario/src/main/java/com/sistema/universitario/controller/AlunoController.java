package com.sistema.universitario.controller;

import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.repositories.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/aluno")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
         }

    @GetMapping
    public List<Aluno> getAll(){
        return this.alunoRepository.findAll();
    }

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno){
        return this.alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoRepository.findById(id)
                .map(save -> { save.setNome(aluno.getNome());
                                save.setRua(aluno.getRua());
                                save.setBairro(aluno.getBairro());
                                save.setCidade(aluno.getCidade());
                                save.setNum(aluno.getNum());
                                save.setCep(aluno.getCep());
                    Aluno newSave = alunoRepository.save(save);
                    return ResponseEntity.ok().body(newSave);})
                .orElse(ResponseEntity.notFound().build());
    }


}
