package com.ProyectoEmpresarial.app.dtoRest;

import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Certificado;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Matricula;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Queja;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Tramite_Traslado;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class FileDto {
	  private String id;
	  private String name;
	  private String type;
	  private byte[] data;

	  private Tramite_Traslado idTraslado;
	  private Long idQueja;

	  private Long idPost;
	  private Long idMatricula;
	  private Long idCertificado;

	  private Long idEmpleado;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Tramite_Traslado getIdTraslado() {
		return idTraslado;
	}

	public void setIdTraslado(Tramite_Traslado idTraslado) {
		this.idTraslado = idTraslado;
	}

	public Long getIdQueja() {
		return idQueja;
	}

	public void setIdQueja(Long idQueja) {
		this.idQueja = idQueja;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public Long getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Long getIdCertificado() {
		return idCertificado;
	}


	public void setIdCertificado(Long idCertificado) {
		this.idCertificado = idCertificado;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}




}
