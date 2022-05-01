package src.main.java.com.sistema.universitario.services;

import java.util.List;

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

    public void updateEmail(Long id, Usuario usuarioRequest) {
        Usuario usuario = findById(id);
        usuario.setEmail(usuarioRequest.getEmail());
        save(usuario);
    }
    public void updateSenha(Long id, Usuario usuarioRequest) {
        Usuario usuario = findById(id);
        usuario.setSenha(usuarioRequest.getSenha());
        save(usuario);
    }


    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }


}
