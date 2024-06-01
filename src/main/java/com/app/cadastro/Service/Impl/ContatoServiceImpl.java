package com.app.cadastro.Service.Impl;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Domain.Repository.ContatoRepository;
import com.app.cadastro.Domain.Repository.PessoaRepository;
import com.app.cadastro.Exception.PessoaNaoEncontradaException;
import com.app.cadastro.Rest.DTO.ContatoDTO;
import com.app.cadastro.Service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private PessoaRepository pessoaRepository ;

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Contato salvaContato(ContatoDTO dto) {
        Integer idPessoa = dto.getPessoa();
        Pessoa pessoa = pessoaRepository
                .findById(idPessoa.longValue())
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setPessoa(pessoa);
        contato.setTelefone(dto.getTelefone());

        contatoRepository.save(contato);

        return contato;
    }

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

}
