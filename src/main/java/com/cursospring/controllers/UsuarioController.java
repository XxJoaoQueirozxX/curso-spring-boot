package com.cursospring.controllers;


import com.cursospring.domain.dto.CredentialsDTO;
import com.cursospring.domain.dto.TokenDTO;
import com.cursospring.domain.dto.UsuarioDTO;
import com.cursospring.domain.entities.Usuario;
import com.cursospring.security.jwt.JwtService;
import com.cursospring.services.UsuarioService;
import com.cursospring.services.exceptions.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario){
        return UsuarioDTO.of(usuarioService.save(usuario));
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody CredentialsDTO credentialsDTO){
        final Usuario usuario = Usuario.builder()
                .username(credentialsDTO.getUsername())
                .password(credentialsDTO.getPassword()).build();

        UserDetails user = usuarioService.autenticar(usuario);
        String token = jwtService.gerarToken(usuario);
        return ResponseEntity.ok(new TokenDTO(usuario.getUsername(), token));
    }

}
