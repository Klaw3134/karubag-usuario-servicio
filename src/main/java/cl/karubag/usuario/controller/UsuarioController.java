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

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

private final UsuarioService usuarioService;


public UsuarioController(UsuarioService usuarioService) {
this.usuarioService = usuarioService;
}


@PostMapping
public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioCreateRequest request) {
UsuarioResponse creado = usuarioService.crear(request);
return new ResponseEntity<>(creado, HttpStatus.CREATED);
}


@GetMapping
public ResponseEntity<List<UsuarioResponse>> listar() {
List<UsuarioResponse> usuarios = usuarioService.listarTodos();
return ResponseEntity.ok(usuarios);
}


@GetMapping("/{id}")
public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
UsuarioResponse usuario = usuarioService.obtenerPorId(id);
return ResponseEntity.ok(usuario);
}


@PutMapping("/{id}")
public ResponseEntity<UsuarioResponse> actualizar(
@PathVariable Long id,
@RequestBody UsuarioCreateRequest request) {
UsuarioResponse actualizado = usuarioService.actualizar(id, request);
return ResponseEntity.ok(actualizado);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable Long id) {
usuarioService.eliminar(id);
return ResponseEntity.noContent().build();
}

@GetMapping("/{id}/exists")
public ResponseEntity<ExistsResponse> existePorId(@PathVariable Long id) {
boolean existe = usuarioService.existePorId(id);
return ResponseEntity.ok(new ExistsResponse(existe));
}
}
