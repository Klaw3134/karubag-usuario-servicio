package cl.karubag.usuario.mapper;

import cl.karubag.usuario.dto.UsuarioCreateRequest;
import cl.karubag.usuario.dto.UsuarioResponse;
import cl.karubag.usuario.model.Usuario;
import org.springframework.stereotype.Component;

/**
* Mapper que convierte entre DTOs y la entidad Usuario.
*
* Centraliza la lógica de conversión para no duplicarla en el Service.
*/
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

/**
* Convierte una entidad Usuario en un DTO de respuesta.
*
* IMPORTANTE: NO incluye el passwordHash en la respuesta (seguridad).
*/
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

/**
* Hash simple del password.
* NOTA: para producción se debe usar BCrypt. Esto es solo demostrativo.
*/
    private String hashPassword(String password) {
// Hash básico para fines académicos (NO usar en producción real)
    return "HASH_" + password.hashCode();
    }
}
