package com.ProyectoEmpresarial.app.entity.Estudiantes;

import java.io.Serializable;

import javax.persistence.*;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Entity
@Table(name = "telefonoEstudiantes")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id" , scope = ContactoEstudiantes.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
public class ContactoEstudiantes implements Serializable  {

	 private static final long serialVersionUID = 1L;
	@Id
	 @Basic(optional = false)
    @Column(name = "id")
	private String id;

	 @Column(name = "Nombre")
	private String nombre ;

	private String apellidoMaterno;

	private String apellidoPaterno;


	private String numeroDocumento;

	@JoinColumn(name = "idrelacionfamiliar", referencedColumnName = "id")
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private Relacionfamiliar idrelacionfamiliar;


	private String numeroTelefono;

	 @JoinColumn(name = "id_Estudiante", referencedColumnName = "id")
	 @ManyToOne(optional = false,fetch = FetchType.LAZY)
	 private Estudiante idEstudiante;



	  @JoinColumn(name = "id_tipo_documento_identidad", referencedColumnName = "id")
	  @ManyToOne(optional = false,fetch = FetchType.LAZY)

	    private TipoDocumentoIdentidad idTipoDocumentoIdentidad;





}
