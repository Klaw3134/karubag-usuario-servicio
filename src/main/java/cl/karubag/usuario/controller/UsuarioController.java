package cl.karubag.usuario.controller;

import cl.karubag.usuario.dto.ExistsResponse;
import cl.karubag.usuario.dto.UsuarioCreateRequest;
import cl.karubag.usuario.dto.UsuarioResponse;
import cl.karubag.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Usuarios", description = "Gestion de usuarios Karübag")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Crear usuario", description = "Crea una nueva cuenta de usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
            content = @Content(schema = @Schema(implementation = UsuarioResponse.class),
            examples = @ExampleObject(value = "{\"email\": \"catalina@karubag.cl\", \"password\": \"password123\", \"nombreCompleto\": \"Catalina Rojas\", \"telefono\": \"+56912345678\", \"rol\": \"CLIENTE\"}"))),
        @ApiResponse(responseCode = "409", description = "Ya existe un usuario con ese email")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del usuario a crear",
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\"email\": \"catalina@karubag.cl\", \"password\": \"password123\", \"nombreCompleto\": \"Catalina Rojas\", \"telefono\": \"+56912345678\", \"rol\": \"CLIENTE\"}")))
        @RequestBody UsuarioCreateRequest request) {
        UsuarioResponse creado = usuarioService.crear(request);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Retorna la lista completa de usuarios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(
            @PathVariable Long id,
            @RequestBody UsuarioCreateRequest request) {
        UsuarioResponse actualizado = usuarioService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verificar existencia", description = "Verifica si un usuario existe — usado por WebClient de otros servicios")
    @ApiResponse(responseCode = "200", description = "Resultado de verificacion")
    @GetMapping("/{id}/exists")
    public ResponseEntity<ExistsResponse> existePorId(@PathVariable Long id) {
        boolean existe = usuarioService.existePorId(id);
        return ResponseEntity.ok(new ExistsResponse(existe));
    }
}
