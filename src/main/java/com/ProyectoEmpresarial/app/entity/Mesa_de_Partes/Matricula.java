package com.ProyectoEmpresarial.app.entity.Mesa_de_Partes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Solicitud;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="matricula")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id" , scope = Matricula.class)
public class Matricula {


	@Id
	private String id;

	private String nombreEstudiante;

	private String apellidoMaternoEstudiante;

	private String apellidoPaternoEstudiante;

	private String tipoDocumentoEstudiante;

	private String numeroDocumentoEstudiante;

	private String TipoMatricula;

	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="idSolicitud")
	private Solicitud idSolicitud;


	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
		private List<File> files;



	public String getTipoMatricula() {
		return TipoMatricula;
	}



	public void setTipoMatricula(String tipoMatricula) {
		TipoMatricula = tipoMatricula;
	}



	public List<File> getFiles() {
		return files;
	}



	public void setFiles(List<File> files) {
		this.files = files;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNombreEstudiante() {
		return nombreEstudiante;
	}



	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}



	public String getApellidoMaternoEstudiante() {
		return apellidoMaternoEstudiante;
	}



	public void setApellidoMaternoEstudiante(String apellidoMaternoEstudiante) {
		this.apellidoMaternoEstudiante = apellidoMaternoEstudiante;
	}



	public String getApellidoPaternoEstudiante() {
		return apellidoPaternoEstudiante;
	}



	public void setApellidoPaternoEstudiante(String apellidoPaternoEstudiante) {
		this.apellidoPaternoEstudiante = apellidoPaternoEstudiante;
	}



	public String getTipoDocumentoEstudiante() {
		return tipoDocumentoEstudiante;
	}



	public void setTipoDocumentoEstudiante(String tipoDocumentoEstudiante) {
		this.tipoDocumentoEstudiante = tipoDocumentoEstudiante;
	}



	public String getNumeroDocumentoEstudiante() {
		return numeroDocumentoEstudiante;
	}



	public void setNumeroDocumentoEstudiante(String numeroDocumentoEstudiante) {
		this.numeroDocumentoEstudiante = numeroDocumentoEstudiante;
	}



	public Solicitud getIdSolicitud() {
		return idSolicitud;
	}



	public void setIdSolicitud(Solicitud idSolicitud) {
		this.idSolicitud = idSolicitud;
	}


}
