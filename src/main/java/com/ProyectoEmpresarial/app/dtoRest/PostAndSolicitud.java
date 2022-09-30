package com.ProyectoEmpresarial.app.dtoRest;

import java.util.List;

public class PostAndSolicitud {



private String id;

private String nombreCompleto;
private String numeroDocumento;

	private String correoPersonal;
	private String tipoReclamo;
	private String action1;
	private List<PostRest> post;





	public String getAction1() {
		return action1;
	}
	public void setAction1(String action1) {
		this.action1 = action1;
	}
	public List<PostRest> getPost() {
		return post;
	}
	public void setPost(List<PostRest> post) {
		this.post = post;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getCorreoPersonal() {
		return correoPersonal;
	}
	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}
	public String getTipoReclamo() {
		return tipoReclamo;
	}
	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}




}
