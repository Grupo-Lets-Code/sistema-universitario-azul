package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.UsuarioNaoEncontradoException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void updateEmail(Long id, Usuario email) {
        Usuario usuario = findById(id);
        usuario.setEmail(email.getEmail());
        save(usuario);
    }
    public void updateSenha(Long id, Usuario senha) {
        Usuario usuario = findById(id);
        usuario.setPassword(senha.getPassword());
        save(usuario);
    }


    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }


    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }


}
