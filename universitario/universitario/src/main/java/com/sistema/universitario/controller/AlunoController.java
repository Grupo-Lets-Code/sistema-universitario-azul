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
                    //FIXME CRIAR A CLASSE ENDEREÇO
                                save.setRua(endereco.getRua());
                                save.setBairro(endereco.getBairro());
                                save.setCidade(endereco.getCidade());
                                save.setNum(endereco.getNum());
                                save.setCep(endereco.getCep());
                    Aluno newSave = alunoRepository.save(save);
                    return ResponseEntity.ok().body(newSave);})
                .orElse(ResponseEntity.notFound().build());
    }

}
