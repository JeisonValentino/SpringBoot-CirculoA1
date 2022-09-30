package com.ProyectoEmpresarial.app.entity.Estudiantes;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@Table(name = "gradoEstudiante")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","idPension","idEstudiante"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder@Getter@Setter
public class GradoEstudiante implements Serializable {


	 private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;

	private String NombreGrado;



	@OneToMany(cascade =CascadeType.ALL  ,mappedBy = "gradoEstudiante_id")
	List<Estudiante> idEstudiante;


		@OneToMany(cascade =CascadeType.ALL  ,mappedBy = "idGradoEstudiante")
		List<Pension> idPension;







}
