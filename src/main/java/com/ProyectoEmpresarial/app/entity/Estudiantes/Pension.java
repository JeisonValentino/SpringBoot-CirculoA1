package com.ProyectoEmpresarial.app.entity.Estudiantes;

import javax.persistence.*;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "pension")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id" , scope = Pension.class)
@Data
@RequiredArgsConstructor
public class Pension {
	@Id
    @Basic(optional = false)
    @Column(name = "id")
	private String id;


	private Double montoPension;


	 @JoinColumn(name = "idEstudiante", referencedColumnName = "id")
	 @ManyToOne(optional = false,fetch = FetchType.LAZY)
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Estudiante idEstudiante;



	 @JoinColumn(name = "idGradoEstudiante", referencedColumnName = "id")
	 @ManyToOne(optional = false,fetch = FetchType.LAZY)
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private GradoEstudiante idGradoEstudiante;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Double getMontoPension() {
		return montoPension;
	}


	public void setMontoPension(Double montoPension) {
		this.montoPension = montoPension;
	}


	public Estudiante getIdEstudiante() {
		return idEstudiante;
	}


	public void setIdEstudiante(Estudiante idEstudiante) {
		this.idEstudiante = idEstudiante;
	}


	public GradoEstudiante getIdGradoEstudiante() {
		return idGradoEstudiante;
	}


	public void setIdGradoEstudiante(GradoEstudiante idGradoEstudiante) {
		this.idGradoEstudiante = idGradoEstudiante;
	}


}
