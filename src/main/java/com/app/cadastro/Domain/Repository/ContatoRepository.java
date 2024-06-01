package com.app.cadastro.Domain.Repository;

import com.app.cadastro.Domain.Entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}

