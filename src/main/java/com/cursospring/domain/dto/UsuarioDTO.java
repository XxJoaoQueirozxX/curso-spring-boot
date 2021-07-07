package com.cursospring.domain.dto;

import com.cursospring.domain.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String username;
    private boolean isAdmin;


    public static UsuarioDTO of(Usuario usuario){
        final UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(usuario.getUsername());
        dto.setId(usuario.getId());
        dto.setAdmin(usuario.isAdmin());
        return dto;
    }

}
