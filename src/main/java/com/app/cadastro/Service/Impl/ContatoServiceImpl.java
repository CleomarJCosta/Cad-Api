package com.app.cadastro.Service.Impl;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Domain.Repository.ContatoRepository;
import com.app.cadastro.Domain.Repository.PessoaRepository;
import com.app.cadastro.Exception.PessoaNaoEncontradaException;
import com.app.cadastro.Rest.DTO.ContatoDTO;
import com.app.cadastro.Service.ContatoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private PessoaRepository pessoaRepository ;

    @Autowired
    private ContatoRepository contatoRepository;

    @Transactional
    @Override
    public Contato salvaContato(ContatoDTO dto) {
        Integer idPessoa = dto.getPessoa();
        Pessoa pessoa = pessoaRepository
                .findById(idPessoa.longValue())
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));

        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setIdPessoa(dto.getPessoa());
        contato.setEmail(dto.getEmail());
        contato.setPessoa(pessoa);
        contato.setTelefone(dto.getTelefone());

        contatoRepository.save(contato);

        // Adiciona o novo contato à lista existente de contatos da pessoa
        List<Contato> contatos = pessoa.getContatos();
        if (contatos == null) {
            contatos = new ArrayList<>();
        }
        contatos.add(contato);
        pessoa.setContatos(contatos);

        pessoaRepository.save(pessoa);
        return contato;
    }

    public Optional<Contato> listarContatos(Long idPessoa) {

        return contatoRepository.findById(idPessoa);

    }



}
