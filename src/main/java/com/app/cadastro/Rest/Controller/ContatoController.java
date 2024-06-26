package com.app.cadastro.Rest.Controller;

import com.app.cadastro.Domain.Entity.Contato;
import com.app.cadastro.Rest.DTO.ContatoDTO;
import com.app.cadastro.Service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/pessoa/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public void Post(@RequestBody ContatoDTO dto){
        contatoService.salvaContato(dto);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Contato>> get(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.listarContatos(id));
    }


}
