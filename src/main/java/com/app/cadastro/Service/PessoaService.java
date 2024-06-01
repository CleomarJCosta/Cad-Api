package com.app.cadastro.Service;

import com.app.cadastro.Domain.Entity.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PessoaService {

    Pessoa salvar(Pessoa pessoa);

    List<Pessoa> listar();

    Optional<Pessoa> listarPorId(Long id);

    Pessoa atualiza(Pessoa pessoa, Long id);

    void deletar(Long id);



}
