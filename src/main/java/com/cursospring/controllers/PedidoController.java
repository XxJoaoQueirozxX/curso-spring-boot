package com.cursospring.controllers;

import com.cursospring.domain.dto.AtualizacaoStatusPedidoDTO;
import com.cursospring.domain.dto.PedidoDTO;
import com.cursospring.domain.entities.Pedido;
import com.cursospring.domain.enums.StatusPedido;
import com.cursospring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody PedidoDTO dto){
        Pedido p = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        service.updateStatus(id, dto);
        return ResponseEntity.noContent().build();
    }
}
