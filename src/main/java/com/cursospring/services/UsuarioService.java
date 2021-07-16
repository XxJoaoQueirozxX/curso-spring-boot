package com.cursospring.services;

import com.cursospring.domain.entities.Usuario;
import com.cursospring.repositories.UsuarioRepository;
import com.cursospring.services.exceptions.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;



    @Transactional
    public Usuario save (Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails details = loadUserByUsername(usuario.getUsername());
        if (passwordEncoder.matches(usuario.getPassword(), details.getPassword())){
            return details;
        }
        throw new SenhaInvalidaException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usúario não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
    }
}
