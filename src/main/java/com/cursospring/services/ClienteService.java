package com.cursospring.services;

import com.cursospring.domain.entities.Cliente;
import com.cursospring.repositories.ClienteRepository;
import com.cursospring.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    public List<Cliente> findAll(Example<Cliente> example){
        return repository.findAll(example);
    }

    public Cliente insert(Cliente c){
        return repository.save(c);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }

    public Cliente update(Long id, Cliente dados){
        Cliente c = findById(id);
        updateData(c, dados);
        repository.save(c);
        return c;
    }

    private void updateData(Cliente cliente, Cliente newData){

        if(newData != null){
            if ( newData.getNome() != null && !newData.getNome().isEmpty()){
                cliente.setNome(newData.getNome());
            }

            if ( newData.getCpf() != null && !newData.getCpf().isEmpty()){
                cliente.setCpf(newData.getCpf());
            }
        }
    }
}
