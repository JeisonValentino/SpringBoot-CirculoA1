package com.ProyectoEmpresarial.app.entity.Estudiantes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Table(name = "estado_Estudiante")
@Data
public class Estado_Estudiante implements Serializable {

	 private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Integer id;

	private String ConceptoEstado;

	private Date fechadeCambio;


	 @OneToMany(mappedBy = "estadoEstudiante_id" , cascade =CascadeType.ALL )
	private List<Estudiante> Estudiantes_id;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getConceptoEstado() {
		return ConceptoEstado;
	}


	public void setConceptoEstado(String conceptoEstado) {
		ConceptoEstado = conceptoEstado;
	}


	public Date getFechadeCambio() {
		return fechadeCambio;
	}


	public void setFechadeCambio(Date fechadeCambio) {
		this.fechadeCambio = fechadeCambio;
	}




}
