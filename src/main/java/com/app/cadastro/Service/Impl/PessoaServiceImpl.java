package com.app.cadastro.Service.Impl;

import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Domain.Repository.PessoaRepository;
import com.app.cadastro.Exception.PessoaNaoEncontradaException;
import com.app.cadastro.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

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
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        Pessoa pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(
                        () -> new PessoaNaoEncontradaException("Pessoa Não Encontrada" + id)
                );
        pessoa.setNome(newPessoa.getNome());
        pessoa.setID(newPessoa.getID());
        pessoa.setContatos(newPessoa.getContatos());
        pessoa.setCPF(newPessoa.getCPF());
        pessoa.setDataNasc(newPessoa.getDataNasc());

        pessoaRepository.save(pessoa);
        return pessoa;
    }

    @Override
    public void deletar(Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        Pessoa pessoa = pessoaRepository
                .findById(id)
                .orElseThrow(
                        () -> new PessoaNaoEncontradaException("Pessoa Não Encontrada" + id)
                );
        pessoaRepository.delete(pessoa);
    }
}
