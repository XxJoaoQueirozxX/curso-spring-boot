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

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Cliente save(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> findAll(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>(){
            public Cliente mapRow(ResultSet rs, int i) throws SQLException{
                return new Cliente(
                    rs.getLong("id"),
                    rs.getString("nome")
                );
            }
        });
    }
}
