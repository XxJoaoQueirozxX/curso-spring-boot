package com.cursospring.services;

import com.cursospring.entities.Produto;
import com.cursospring.repositories.ProdutoRepository;
import com.cursospring.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;


    public List<Produto> findAll(){
        return repository.findAll();
    }

    public List<Produto> findAll(Example<Produto> example){
        return repository.findAll(example);
    }

    public Produto findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum produto encontrado para o id: " + id));
    }

   public void delete(Long id) {
        repository.delete(findById(id));
   }

   public Produto insert(Produto p){
        return repository.save(p);
   }

   public Produto update(Long id, Produto dados){
        Produto p = findById(id);
        updateDados(p, dados);
        return repository.save(p);
   }

   private void updateDados(Produto entity, Produto dados){
        entity.setDescricao(dados.getDescricao());
        entity.setPreco(dados.getPreco());
   }

}
