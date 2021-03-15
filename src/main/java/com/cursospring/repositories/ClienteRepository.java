package com.cursospring.repositories;

import com.cursospring.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Cliente> findAll(){
        return entityManager.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Transactional
    public Cliente save(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente c){
        return entityManager.merge(c);
    }

    @Transactional
    public void delete(Cliente c){
        if (!entityManager.contains(c)){
            c = entityManager.merge(c);
        }
        entityManager.remove(c);
    }

    @Transactional
    public void delete(Long id){
        Cliente c = entityManager.find(Cliente.class, id);
        delete(c);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByName(String name){
        String jpql = "select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome",  "%" + name +"%");
        return query.getResultList();
    }

}
