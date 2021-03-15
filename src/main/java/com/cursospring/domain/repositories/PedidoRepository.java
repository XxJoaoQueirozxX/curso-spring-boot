package com.cursospring.domain.repositories;

import com.cursospring.domain.entities.Cliente;
import com.cursospring.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);
}
