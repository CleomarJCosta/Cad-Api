package com.app.cadastro.Service.Impl;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Domain.Repository.ContatoRepository;
import com.app.cadastro.Domain.Repository.PessoaRepository;
import com.app.cadastro.Exception.PessoaNaoEncontradaException;
import com.app.cadastro.Rest.DTO.ContatoDTO;
import com.app.cadastro.Service.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Override
    public Optional<Pessoa> listarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public Pessoa atualiza(Pessoa newPessoa, Long id) {
        Pessoa pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(
                        () -> new PessoaNaoEncontradaException("Pessoa Não Encontrada" + id)
                );

        pessoa.setNome(newPessoa.getNome());
        pessoa.setCPF(newPessoa.getCPF());
        pessoa.setDataNasc(newPessoa.getDataNasc());

        pessoaRepository.save(pessoa);
        return pessoa;
    }


    @Transactional
    @Override
    public void deletar(Long idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada: " + idPessoa));

        // Remover todos os contatos associados à pessoa
        for (Contato contato : pessoa.getContatos()) {
            contato.setPessoa(null); // Desvincular o contato da pessoa
            contatoRepository.save(contato); // Salvar o contato atualizado
        }

        pessoaRepository.delete(pessoa); // Excluir a pessoa
    }

}
