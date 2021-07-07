package com.cursospring.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;

    @Column
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;

    @Column
    private boolean admin;


}
