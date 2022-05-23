package com.sistema.universitario.services;

import com.sistema.universitario.exceptions.endereco.EnderecoInexistenteException;
import com.sistema.universitario.models.Endereco;
import com.sistema.universitario.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;


    public EnderecoService(EnderecoRepository enderecoRepository) {this.enderecoRepository = enderecoRepository;}

    public Endereco saveEndereco(Endereco endereco){return enderecoRepository.save(endereco);}

    public Endereco updateEndereco(Long id, Endereco endereco){
        Endereco entidade = this.findEnderecoById(id);
        entidade.setNum(endereco.getNum());
        entidade.setRua(endereco.getRua());
        entidade.setBairro(endereco.getBairro());
        entidade.setCidade(endereco.getCidade());
        entidade.setCep(endereco.getCep());
        return this.enderecoRepository.save(entidade);
    }

    public void deleteEndereco(Long id){
        Endereco entidade = this.findEnderecoById(id);
        this.enderecoRepository.delete(entidade);
    }

    public List<Endereco> listAll(){
        return this.enderecoRepository.findAll();
    }

    public Endereco findEnderecoById(Long id){
        return this.enderecoRepository.findById(id).orElseThrow(EnderecoInexistenteException::new);
    }
}
