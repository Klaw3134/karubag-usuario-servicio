package cl.karubag.usuario.dto;

import cl.karubag.usuario.model.Rol;
import java.time.LocalDateTime;

public class UsuarioResponse {

private Long id;
private String email;
private String nombreCompleto;
private String telefono;
private Rol rol;
private Boolean activo;
private LocalDateTime creadoEn;
private LocalDateTime actualizadoEn;


public UsuarioResponse() {
}

public UsuarioResponse(Long id, String email, String nombreCompleto, String telefono,
Rol rol, Boolean activo, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
this.id = id;
this.email = email;
this.nombreCompleto = nombreCompleto;
this.telefono = telefono;
this.rol = rol;
this.activo = activo;
this.creadoEn = creadoEn;
this.actualizadoEn = actualizadoEn;
}


public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getNombreCompleto() {
return nombreCompleto;
}

public void setNombreCompleto(String nombreCompleto) {
this.nombreCompleto = nombreCompleto;
}

public String getTelefono() {
return telefono;
}

public void setTelefono(String telefono) {
this.telefono = telefono;
}

public Rol getRol() {
return rol;
}

public void setRol(Rol rol) {
this.rol = rol;
}

public Boolean getActivo() {
return activo;
}

public void setActivo(Boolean activo) {
this.activo = activo;
}

public LocalDateTime getCreadoEn() {
return creadoEn;
}

public void setCreadoEn(LocalDateTime creadoEn) {
this.creadoEn = creadoEn;
}

public LocalDateTime getActualizadoEn() {
return actualizadoEn;
}

public void setActualizadoEn(LocalDateTime actualizadoEn) {
this.actualizadoEn = actualizadoEn;
}
}
