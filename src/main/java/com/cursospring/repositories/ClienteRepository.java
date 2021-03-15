package com.cursospring.repositories;

import com.cursospring.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static final String UPDATE = "UPDATE CLIENTE SET NOME=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM CLIENTE WHERE ID=?";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Cliente save(Cliente cliente){
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public List<Cliente> findAll(){
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            public Cliente mapRow(ResultSet rs, int i) throws SQLException {
                return new Cliente(
                        rs.getLong("id"),
                        rs.getString("nome")
                );
            }
        };
    }

    public List<Cliente> findByName(String name){
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE NOME LIKE ?"), new String[]{"%"+name+"%"},  getRowMapper());
    }


    public Cliente update(Cliente c){
        jdbcTemplate.update(UPDATE, c.getNome(), c.getId());
        return c;
    }

    public void delete(Cliente c){
        delete(c.getId());
    }

    public void delete(Long id){
        jdbcTemplate.update(DELETE, id);
    }


}
