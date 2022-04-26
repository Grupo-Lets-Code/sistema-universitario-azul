package com.sistema.universitario.controller;

import com.sistema.universitario.models.Disciplina;
import com.sistema.universitario.repositories.DisciplinaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping
    public List<Disciplina> getAllDiscplina() {
        return this.disciplinaRepository.findAll();
    }

    @PostMapping
    public Disciplina save(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        return disciplinaRepository.findById(id)
                .map(save -> { save.setNome(disciplina.getNome());
                                Disciplina newSave = disciplinaRepository.save(save);
                                return ResponseEntity.ok().body(newSave);})
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(delete -> {
                    disciplinaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
