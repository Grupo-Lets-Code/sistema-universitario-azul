package com.sistema.universitario.controller;

import com.sistema.universitario.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final com.sistema.universitario.repositories.UsuarioRepository usuarioRepository;
    public UsuarioController(com.sistema.universitario.repositories.UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getAllUsers(){
        return this.usuarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuarios) {
        return this.usuarioRepository.save(usuarios);
    }
}
