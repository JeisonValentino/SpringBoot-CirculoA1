package com.ProyectoEmpresarial.app.entity.Estudiantes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "loginEstudiantes")
@Data
public class LoginEstudiantes implements Serializable {

	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Integer id;

	@Column(nullable=false , length=50, unique = true)
	private String correo;


   @Column(name = "fecha_registro")
   @CreatedDate
   private Date fechaRegistro;

   @Basic(optional = false)
   @Column(name = "contraseña")
   private String contraseña;



	@JoinColumn(name = "id_estudiante", referencedColumnName = "id", unique = true)
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private Estudiante estudiante_id;




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public Estudiante getEstudiante_id() {
		return estudiante_id;
	}


	public void setEstudiante_id(Estudiante estudiante_id) {
		this.estudiante_id = estudiante_id;
	}





}
