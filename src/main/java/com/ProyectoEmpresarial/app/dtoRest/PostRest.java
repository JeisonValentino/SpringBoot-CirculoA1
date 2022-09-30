package com.ProyectoEmpresarial.app.dtoRest;

import java.util.List;

import com.ProyectoEmpresarial.app.entity.File;

public class PostRest {
private Long id;


private String titulo;

	private String concepto;

	private String mensaje;

	private String nombreEmpleado;

	private String cargo;

	private String idSolicitud ;


	private List<byte[]> photo;


	public List<byte[]> getPhoto() {
		return photo;
	}

	public void setPhoto(List<byte[]> photo) {
		this.photo = photo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}





}
