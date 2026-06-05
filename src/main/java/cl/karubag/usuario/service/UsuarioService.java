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

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
    this.usuarioRepository = usuarioRepository;
    this.usuarioMapper = usuarioMapper;
    }


    @Transactional
    public UsuarioResponse crear(UsuarioCreateRequest request) {
        
        if (usuarioRepository.existsByEmail(request.getEmail())) {
        throw new DuplicateResourceException(
        "Ya existe un usuario con el email: " + request.getEmail()
            );
         }

        Usuario usuario = usuarioMapper.toEntity(request);
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(guardado);
    }

    
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
        "Usuario con id " + id + " no existe"
        ));
        return usuarioMapper.toResponse(usuario);
    }

    
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
        .map(usuarioMapper::toResponse)
        .collect(Collectors.toList());
    }

    
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    
    @Transactional
    public UsuarioResponse actualizar(Long id, UsuarioCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        "Usuario con id " + id + " no existe"
        ));


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
