package com.app.cadastro.Service;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Rest.DTO.ContatoDTO;

import java.util.Optional;

public interface ContatoService {
    Contato salvaContato(ContatoDTO dto);
    Optional<Contato> listarContatos(Long idPessoa);
}
