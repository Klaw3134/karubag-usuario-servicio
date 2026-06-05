package cl.karubag.usuario.dto;

import cl.karubag.usuario.model.Rol;

public class UsuarioCreateRequest {

private String email;
private String password;
private String nombreCompleto;
private String telefono;
private Rol rol;



public UsuarioCreateRequest() {
}

public UsuarioCreateRequest(String email, String password, String nombreCompleto, String telefono, Rol rol) {
this.email = email;
this.password = password;
this.nombreCompleto = nombreCompleto;
this.telefono = telefono;
this.rol = rol;
}


public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
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
}