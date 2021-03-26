package com.cursospring.controllers;

import com.cursospring.domain.entities.Cliente;
import com.cursospring.domain.entities.Pedido;
import com.cursospring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;


    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Cliente> example = Example.of(filtro, matcher);
        List<Cliente> clientes = service.findAll(example);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody @Valid Cliente cliente){
        cliente.setId(null);
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
        cliente.setId(null);
        cliente = service.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }


    @GetMapping(value = "/{id}/pedidos")
    public ResponseEntity<Set<Pedido>> getClientePedidos(@PathVariable Long id){
        Cliente c = service.findById(id);
        return ResponseEntity.ok(c.getPedidos());
    }
}
