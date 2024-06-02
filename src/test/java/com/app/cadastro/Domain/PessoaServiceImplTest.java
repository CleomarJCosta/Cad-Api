package com.app.cadastro.Domain;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Domain.Repository.PessoaRepository;
import com.app.cadastro.Service.Impl.PessoaServiceImpl;
import com.app.cadastro.Service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PessoaServiceImplTest {

    @InjectMocks
    private PessoaService pessoaService = new PessoaServiceImpl();

    @Mock
    private PessoaRepository pessoaRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarPessoaComContatos() {
        // Criando uma pessoa com contatos
        Pessoa pessoa = new Pessoa();
        pessoa.setID(1L);
        pessoa.setNome("João Carlos");
        pessoa.setCPF("12345678900");
        pessoa.setDataNasc(LocalDate.of(1990, 1, 1));

        Contato contato1 = new Contato();
        contato1.setID(1L);
        contato1.setNome("Contato 1");
        contato1.setEmail("contato1@test.com");
        contato1.setTelefone("123456789");
        contato1.setPessoa(pessoa);

        List<Contato> contatos = new ArrayList<>();
        contatos.add(contato1);

        pessoa.setContatos(contatos);


        when(pessoaRepository.save(any(Pessoa.class))).thenAnswer(invocation -> {
            Pessoa pessoaToSave = invocation.getArgument(0);
            pessoaToSave.setID(1L);
            return pessoaToSave;
        });


        Pessoa savedPessoa = pessoaService.salvar(pessoa);


        verify(pessoaRepository, times(1)).save(any(Pessoa.class));


        assertThat(savedPessoa).isNotNull();
        assertThat(savedPessoa.getID()).isEqualTo(1L);
        assertThat(savedPessoa.getNome()).isEqualTo("João Carlos");
        assertThat(savedPessoa.getCPF()).isEqualTo("12345678900");
        assertThat(savedPessoa.getDataNasc()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(savedPessoa.getContatos()).hasSize(1);
        assertThat(savedPessoa.getContatos().get(0)).isEqualTo(contato1);
    }
}





