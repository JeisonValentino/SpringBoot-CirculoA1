package com.ProyectoEmpresarial.app.dtoRest;

public class UserDetailRequestModel {

private Long id;
private String correo;
private String contraseña;
private Long idEmpleado;
private String UserId;
private String role;

private Long idRole;



public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserId() {
	return UserId;
}
public void setUserId(String userId) {
	UserId = userId;
}
public Long getIdRole() {
	return idRole;
}
public void setIdRole(Long idRole) {
	this.idRole = idRole;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public Long getIdEmpleado() {
	return idEmpleado;
}
public void setIdEmpleado(Long idEmpleado) {
	this.idEmpleado = idEmpleado;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getContraseña() {
	return contraseña;
}
public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}




}
