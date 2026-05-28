package cl.karubag.usuario.service;

import cl.karubag.usuario.dto.UsuarioCreateRequest;
import cl.karubag.usuario.dto.UsuarioResponse;
import cl.karubag.usuario.exception.DuplicateResourceException;
import cl.karubag.usuario.exception.ResourceNotFoundException;
import cl.karubag.usuario.mapper.UsuarioMapper;
import cl.karubag.usuario.model.Usuario;
import cl.karubag.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* Service que contiene la lógica de negocio de Usuario.
*
* Es el intermediario entre el Controller (HTTP) y el Repository (BD).
* Aplica reglas de negocio como:
* - Validar emails únicos antes de crear
* - Convertir DTOs a entidades y viceversa (usando UsuarioMapper)
* - Lanzar excepciones cuando algo no se encuentra o está duplicado
*/
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    /**
    * Inyección de dependencias por constructor (la forma recomendada).
    * Spring automáticamente provee las instancias del Repository y Mapper.
    */
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
    this.usuarioRepository = usuarioRepository;
    this.usuarioMapper = usuarioMapper;
    }

    // ===== CREATE =====

    /**
    * Crea un nuevo usuario.
    * Valida que el email no exista previamente.
    */
    @Transactional
    public UsuarioResponse crear(UsuarioCreateRequest request) {
        // Regla de negocio: el email debe ser único
        if (usuarioRepository.existsByEmail(request.getEmail())) {
        throw new DuplicateResourceException(
        "Ya existe un usuario con el email: " + request.getEmail()
            );
         }

        Usuario usuario = usuarioMapper.toEntity(request);
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(guardado);
    }

    // ===== READ =====

    /**
    * Obtiene un usuario por su ID.
    * Lanza ResourceNotFoundException (404) si no existe.
    */
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
        "Usuario con id " + id + " no existe"
        ));
        return usuarioMapper.toResponse(usuario);
    }

    /**
    * Lista todos los usuarios del sistema.
    */
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
        .map(usuarioMapper::toResponse)
        .collect(Collectors.toList());
    }

    /**
    * Verifica si existe un usuario con el id dado.
    * Usado por otros microservicios para validaciones cruzadas.
    */
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    // ===== UPDATE =====

    /**
    * Actualiza datos básicos de un usuario existente.
    */
    @Transactional
    public UsuarioResponse actualizar(Long id, UsuarioCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        "Usuario con id " + id + " no existe"
        ));

    // Si cambia el email, validar que no exista
        if (!usuario.getEmail().equals(request.getEmail())
            && usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
            "Ya existe un usuario con el email: " + request.getEmail()
            );
    }

    usuario.setEmail(request.getEmail());
    usuario.setNombreCompleto(request.getNombreCompleto());
    usuario.setTelefono(request.getTelefono());
    usuario.setRol(request.getRol());

    Usuario actualizado = usuarioRepository.save(usuario);
    return usuarioMapper.toResponse(actualizado);
    }

    // ===== DELETE =====

    /**
    * Elimina un usuario por su ID.
    * Lanza ResourceNotFoundException (404) si no existe.
    */
    @Transactional
    public void eliminar(Long id) {
    if (!usuarioRepository.existsById(id)) {
    throw new ResourceNotFoundException(
    "Usuario con id " + id + " no existe"
    );
    }
    usuarioRepository.deleteById(id);
    }
}
