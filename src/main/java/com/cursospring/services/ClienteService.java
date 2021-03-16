package com.cursospring.services;

import com.cursospring.entities.Cliente;
import com.cursospring.repositories.ClienteRepository;
import com.cursospring.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;


    public Cliente findById(Long id){
        Optional<Cliente> c = repository.findById(id);
        return c.orElseThrow(() -> new NotFoundException("Nenhum cliente encontrado para o id: " + id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }
}
