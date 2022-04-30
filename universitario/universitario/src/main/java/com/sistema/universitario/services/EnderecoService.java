package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.EnderecoInexistenteException;
import com.sistema.universitario.exceptions.EnderecoJaCadastradoException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;


    public EnderecoService(EnderecoRepository enderecoRepository) {this.enderecoRepository = enderecoRepository;}

    public Endereco saveEndereco(Endereco endereco){
        if (!this.enderecoRepository.existsById(endereco.getId())){
            return this.enderecoRepository.save(endereco);
        } else {
            throw new EnderecoJaCadastradoException();
        }
    }

    public Endereco updateEndereco(long id, Endereco endereco){
        Endereco entidade = this.findEnderecoById(endereco.getId());
        entidade.setNum(endereco.getNum());
        entidade.setRua(endereco.getRua());
        entidade.setBairro(endereco.getBairro());
        entidade.setCidade(endereco.getCidade());
        entidade.setCep(endereco.getCep());
        return this.enderecoRepository.save(entidade);
    }

    public void deleteEndereco(long id){
        Endereco entidade = this.findEnderecoById(id);
        this.enderecoRepository.delete(entidade);
    }

    public List<Endereco> listAll(){
        return this.enderecoRepository.findAll();
    }

    public Endereco findEnderecoById(long id){
        return this.enderecoRepository.findById(id).orElseThrow(EnderecoInexistenteException::new);
    }
}
