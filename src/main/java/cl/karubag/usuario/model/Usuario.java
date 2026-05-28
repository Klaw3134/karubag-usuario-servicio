package cl.karubag.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
* Entidad Usuario - representa una cuenta del sistema Karubag.
*
* Esta clase define la estructura de la tabla "usuario" en la BD.
* Hibernate la crea automáticamente al arrancar la app (code-first).
*/
@Entity
@Table(name = "usuario")
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "email", nullable = false, unique = true, length = 255)
private String email;

@Column(name = "password_hash", nullable = false, length = 255)
private String passwordHash;

@Column(name = "nombre_completo", nullable = false, length = 150)
private String nombreCompleto;

@Column(name = "telefono", length = 30)
private String telefono;

@Enumerated(EnumType.STRING)
@Column(name = "rol", nullable = false, length = 30)
private Rol rol;

@Column(name = "activo", nullable = false)
private Boolean activo = true;

@Column(name = "creado_en", nullable = false, updatable = false)
private LocalDateTime creadoEn;

@Column(name = "actualizado_en", nullable = false)
private LocalDateTime actualizadoEn;

// ===== Callbacks de ciclo de vida =====
// Se ejecutan automáticamente antes de guardar/actualizar en BD

@PrePersist
protected void onCreate() {
this.creadoEn = LocalDateTime.now();
this.actualizadoEn = LocalDateTime.now();
}

@PreUpdate
protected void onUpdate() {
this.actualizadoEn = LocalDateTime.now();
}

// ===== Constructores =====

public Usuario() {
// JPA requiere constructor vacío sin argumentos
}

public Usuario(String email, String passwordHash, String nombreCompleto, String telefono, Rol rol) {
this.email = email;
this.passwordHash = passwordHash;
this.nombreCompleto = nombreCompleto;
this.telefono = telefono;
this.rol = rol;
this.activo = true;
}

// ===== Getters y Setters =====

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

public String getPasswordHash() {
return passwordHash;
}

public void setPasswordHash(String passwordHash) {
this.passwordHash = passwordHash;
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

public LocalDateTime getActualizadoEn() {
return actualizadoEn;
}
}
