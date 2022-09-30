
package com.ProyectoEmpresarial.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

/**
 *
 * @author Stewen Jordi Mateo Villanueva <smateovj@gmail.com>
 */
@Entity
@Table(name = "tipo_documento_identidad")
@JsonIgnoreProperties({"empleadoList","idContactoEstudiante","idEstudiante","hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TipoDocumentoIdentidad implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_documento")
    private String nombreDocumento;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumentoIdentidad")
    private List<Empleado> empleadoList;


    @OneToMany(cascade =CascadeType.ALL  ,mappedBy = "idTipoDocumentoIdentidad")
	List<ContactoEstudiantes> idContactoEstudiante;


    @OneToMany(cascade =CascadeType.ALL  ,mappedBy = "idTipoDocumentoIdentidad")
  	List<Estudiante> idEstudiante;






}
