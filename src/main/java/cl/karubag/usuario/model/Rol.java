package cl.karubag.usuario.model;

/* Tipos de usuario del sistema Karubag.
*
* - ADMIN: gestiona el sistema (planes, materiales, asignación de rutas)
* - OPERADOR: trabaja en terreno (registra pesajes y completa retiros)
* - CLIENTE: usuario final (recibe el servicio de retiro)
*/
public enum Rol {
ADMIN,
OPERADOR,
CLIENTE
}
