package cl.karubag.usuario.controller;

import cl.karubag.usuario.dto.ExistsResponse;
import cl.karubag.usuario.dto.UsuarioCreateRequest;
import cl.karubag.usuario.dto.UsuarioResponse;
import cl.karubag.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Controller REST para la gestión de usuarios.
*
* Expone los endpoints HTTP del microservicio usuario-servicio.
* Todos los endpoints están bajo /api/v1/usuarios.
*
* Aplica los códigos HTTP estándar:
* - 200 OK: operación exitosa con respuesta
* - 201 Created: recurso creado
* - 204 No Content: operación exitosa sin respuesta (DELETE)
* - 404 Not Found: recurso no encontrado (manejado por GlobalExceptionHandler)
* - 409 Conflict: recurso duplicado (manejado por GlobalExceptionHandler)
*/
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

private final UsuarioService usuarioService;

/**
* Inyección de dependencia por constructor.
*/
public UsuarioController(UsuarioService usuarioService) {
this.usuarioService = usuarioService;
}

// ===== POST /api/v1/usuarios — crear usuario =====
@PostMapping
public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioCreateRequest request) {
UsuarioResponse creado = usuarioService.crear(request);
return new ResponseEntity<>(creado, HttpStatus.CREATED);
}

// ===== GET /api/v1/usuarios — listar todos =====
@GetMapping
public ResponseEntity<List<UsuarioResponse>> listar() {
List<UsuarioResponse> usuarios = usuarioService.listarTodos();
return ResponseEntity.ok(usuarios);
}

// ===== GET /api/v1/usuarios/{id} — obtener uno por id =====
@GetMapping("/{id}")
public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
UsuarioResponse usuario = usuarioService.obtenerPorId(id);
return ResponseEntity.ok(usuario);
}

// ===== PUT /api/v1/usuarios/{id} — actualizar =====
@PutMapping("/{id}")
public ResponseEntity<UsuarioResponse> actualizar(
@PathVariable Long id,
@RequestBody UsuarioCreateRequest request) {
UsuarioResponse actualizado = usuarioService.actualizar(id, request);
return ResponseEntity.ok(actualizado);
}

// ===== DELETE /api/v1/usuarios/{id} — eliminar =====
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable Long id) {
usuarioService.eliminar(id);
return ResponseEntity.noContent().build();
}

// ===== GET /api/v1/usuarios/{id}/exists — validación cruzada =====
// Este endpoint es consultado por otros microservicios (cliente, ruta, etc.)
@GetMapping("/{id}/exists")
public ResponseEntity<ExistsResponse> existePorId(@PathVariable Long id) {
boolean existe = usuarioService.existePorId(id);
return ResponseEntity.ok(new ExistsResponse(existe));
}
}
