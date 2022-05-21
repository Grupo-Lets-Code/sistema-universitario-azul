package com.sistema.universitario.testesUnitariosService;

import com.sistema.universitario.models.Aluno;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.models.StatusUsuario;
import com.sistema.universitario.models.Usuario;
import com.sistema.universitario.repositories.AlunoRepository;
import com.sistema.universitario.services.AlunoService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Teste listar todos - Usuarios")
    void findALlUsuarios() {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(
                new Usuario("teste@colegio.com", "1234");

        when(usuarioRepository.findAll())
                .thenReturn(usuarioList);

        List<Usuario> usuarios = usuarioService.findAll();
        Assertions.assertNotNull(usurios);
        Assertions.assertFalse(usuarios.isEmpty());
        Assertions.assertEquals(1, usuarioList.size());
    }

    @Test
    @DisplayName("Teste cadastrar - Usuario")
    void saveUsuario() {
        Usuario usuarioSave = new Usuario();
        usuarioSave.setEmail("rafael@colegio.com");
        usuarioSave.setPassword("12345");

        Usuario usuarioReturn = new Usuario();
        usuarioReturn.setEmail("rafael@colegio.com");
        usuarioReturn.setPassword("12345");


        when(usuarioRepository.save(usuarioSave))
                .thenReturn(usuarioReturn);

        usuarioReturn = usuarioService.save(usuarioSave);

        Assertions.assertNotNull(usuarioReturn);
        Assertions.assertEquals(usuarioSave.getEmail(), usuarioReturn.getEmail());
        Assertions.assertEquals(usuarioSave.getPassword(), usuarioReturn.getPassword());
    }

    @Test
    @DisplayName("Teste deletar - Usuario")
    void deleteUsuarioStatus() {
        Usuario usuario = new Usuario();
        usuario.setEmail("allan@colegio.com");
        usuario.setPassword("12345");

        when(usuarioRepository.findById((long) 2))
                .thenReturn(Optional.of(usuario));

        usuarioService.deleteUsuarioStatus((long) 2);

    }

    @Test
    @DisplayName("Teste atualizar - Usuario")
    void updateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("marcos@colegio.com");
        usuario.setPassword("12345");

        Usuario usuarioNovo = new Usuario();

       usuarioNovo.setEmail("allan@colegio.com");

        when(usuarioRepository.findById((long)1))
                .thenReturn(Optional.of(usuario));

        usuarioService.update((long) 1 , usuarioNovo);

        verify(usuarioRepository).save(usuario);

        verify(usuarioRepository).findById((long) 1);

    }

}
