package com.app.cadastro.Rest.DTO;

import com.app.cadastro.Domain.Entity.Contato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

public class InformacoesPessoaDTO {

    private Long ID;


    private String nome;


    private String CPF;


    private LocalDate dataNasc;

    private Set<Contato> contatos;
}
