package com.ProyectoEmpresarial.app.entity.Estudiantes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@Table(name = "Relacionfamiliar")
@JsonIgnoreProperties({"contactoEstudiantes","hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Relacionfamiliar implements Serializable  {

	 private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id",nullable = false)
		private Integer id;


		private String concepto;

		  @OneToMany(mappedBy = "idrelacionfamiliar", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
		  private List<ContactoEstudiantes> contactoEstudiantes;


}
