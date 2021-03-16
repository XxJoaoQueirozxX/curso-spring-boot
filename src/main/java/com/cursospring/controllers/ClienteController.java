package com.cursospring.controllers;

import com.cursospring.entities.Cliente;
import com.cursospring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        List<Cliente> clientes = service.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }





}
