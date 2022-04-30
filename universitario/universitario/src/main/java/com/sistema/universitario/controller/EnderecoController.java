package com.sistema.universitario.controller;

import com.sistema.universitario.exceptions.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.EnderecoJaCadastradoException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.services.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/endereco")
@Slf4j
public class EnderecoController {

    private final EnderecoService enderecoService;


    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("{id}")
    public ResponseEntity selecionaEnderecoById(@PathVariable("id") long id){
        Endereco endereco = this.enderecoService.findEnderecoById(id);
        ResponseEntity response = new ResponseEntity(endereco, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity salvarEndereco(@Valid Endereco endereco, BindingResult result){
        this.enderecoService.saveEndereco(endereco);
        ResponseEntity response = new ResponseEntity(HttpStatus.CREATED);
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarEndereco(@PathVariable("id") long id, @RequestBody Endereco endereco){
        this.enderecoService.updateEndereco(id, endereco);
        ResponseEntity response = new ResponseEntity("Endereço atualizado com sucesso", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarEndereco(@PathVariable("id") long id){
        this.enderecoService.deleteEndereco(id);
        return ResponseEntity.ok("Endereço deletado com sucesso");
    }

}
