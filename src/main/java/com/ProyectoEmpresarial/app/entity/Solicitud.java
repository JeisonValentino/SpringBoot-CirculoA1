package com.ProyectoEmpresarial.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Certificado;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Matricula;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Queja;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Tramite_Traslado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@Table(name="solicitud")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id" , scope = Solicitud.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Solicitud {


	@Id
	private String id;

	private String nombre;

	private String apellidoMaterno;

	private String apellidoPaterno;

	private String tipoDni;

	private String numeroDocumento;

	private String correoPersonal;

	private String domicilio;

	private String TipoSolicitud;

	@OneToOne(cascade=CascadeType.ALL,mappedBy = "idSolicitud")
	private Matricula matricula;
	@OneToOne(cascade=CascadeType.ALL,mappedBy = "idSolicitud")
	private Queja queja;
	@OneToOne(cascade=CascadeType.ALL,mappedBy = "idSolicitud")
	private Certificado certificado;

	@OneToOne(cascade=CascadeType.ALL,mappedBy = "idSolicitud")
	private Tramite_Traslado traslado;

	@JsonBackReference(value="solicitudPost")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="idSolicitud", fetch=FetchType.LAZY)
	private List<Post>post;











}
