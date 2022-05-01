package com.sistema.universitario.controller;

import com.sistema.universitario.models.Professor;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll(){
        return this.usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Usuario usuario) {
        this.usuarioService.save(usuario);
        return new ResponseEntity("Usuario(a) criado(a) com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping({"{idUsuario}/{email}"})
    public ResponseEntity updateEmail(@PathVariable("idUsuario") Long idUsuario,
                                                    @PathVariable("email") Usuario email) {
        usuarioService.updateEmail(idUsuario, email);
        return new ResponseEntity("Email alterado com sucesso!", HttpStatus.OK);
    }

    @PutMapping({"{idUsuario}/{senha}"})
    public ResponseEntity updateSenha(@PathVariable("idUsuario") Long idUsuario,
                                      @PathVariable("senha") Usuario senha) {
        usuarioService.updateEmail(idUsuario, senha);
        return new ResponseEntity("Senha alterada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        usuarioService.delete(id);
        return new ResponseEntity("Usuario(a) excluído(a) com sucesso!", HttpStatus.OK);
    }

}
