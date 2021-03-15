package com.cursospring.repositories;

import com.cursospring.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);
}
