package cl.karubag.usuario.mapper;

import cl.karubag.usuario.dto.UsuarioCreateRequest;
import cl.karubag.usuario.dto.UsuarioResponse;
import cl.karubag.usuario.model.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {


    public Usuario toEntity(UsuarioCreateRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(hashPassword(dto.getPassword()));
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setActivo(true);
        return usuario;
    }


    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
        usuario.getId(),
        usuario.getEmail(),
        usuario.getNombreCompleto(),
        usuario.getTelefono(),
        usuario.getRol(),
        usuario.getActivo(),
        usuario.getCreadoEn(),
        usuario.getActualizadoEn()
        );
    }


    private String hashPassword(String password) {
    return "HASH_" + password.hashCode();
    }
}
