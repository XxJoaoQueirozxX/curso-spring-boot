package com.cursospring.services;

import com.cursospring.domain.entities.ItemPedido;
import com.cursospring.repositories.ItemPedidoRepository;
import com.cursospring.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository repository;


    public ItemPedido findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("nenhum item de pedido encontrado para o ID: " + id));
    }

    public ItemPedido insert(ItemPedido item){
        return repository.save(item);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }
}

