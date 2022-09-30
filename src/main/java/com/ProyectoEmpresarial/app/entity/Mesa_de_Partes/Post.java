package com.ProyectoEmpresarial.app.entity.Mesa_de_Partes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Solicitud;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name="post")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id" , scope = Post.class)
public class Post implements Serializable {
	 private static final long serialVersionUID = 1L;
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "id")
private Long id;

	 private String titulo;
	private String concepto;
	 @Lob @Basic (fetch = FetchType.LAZY) @Column (columnDefinition = "text")
	private String mensaje;




	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	 @JoinColumn(name = "id_Solicitud", referencedColumnName = "id" )
	private Solicitud idSolicitud;


	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	 @JoinColumn(name = "id_Empleado", referencedColumnName = "id"  )
	private Empleado idEmpleado;


	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPost")
		private List<File> files;


	public List<File> getFiles() {
		return files;
	}


	public void setFiles(List<File> files) {
		this.files = files;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Empleado getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(Empleado idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getConcepto() {
		return concepto;
	}


	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}








	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Solicitud getIdSolicitud() {
		return idSolicitud;
	}


	public void setIdSolicitud(Solicitud idSolicitud) {
		this.idSolicitud = idSolicitud;
	}



}
