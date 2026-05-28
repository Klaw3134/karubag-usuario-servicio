package cl.karubag.usuario.dto;

/**
* DTO simple para responder consultas de existencia.
*
* Se usa cuando otros microservicios (como cliente-servicio) preguntan:
* "¿existe el usuario con id 5?" → respuesta: { "exists": true }
*/
public class ExistsResponse {

private boolean exists;

public ExistsResponse() {
}

public ExistsResponse(boolean exists) {
this.exists = exists;
}

public boolean isExists() {
return exists;
}

public void setExists(boolean exists) {
this.exists = exists;
}
}
