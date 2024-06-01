package com.app.cadastro.Rest.Controller;

import com.app.cadastro.Domain.Entity.Pessoa;
import com.app.cadastro.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody Pessoa pessoa){
        pessoaService.salvar(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listar());
    }
    @GetMapping("{Id}")
    public Optional<Pessoa> getById(@PathVariable Long Id){
        return pessoaService.listarPorId(Id);
    }
    @DeleteMapping("{Id}")
    public void delete(Long Id){
        pessoaService.deletar(Id);
    }













}
