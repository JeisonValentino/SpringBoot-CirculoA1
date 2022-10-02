package com.ProyectoEmpresarial.app.entity.Estudiantes;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Sede;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;

@Entity
@Table(name = "estudiante")
@JsonIgnoreProperties({ "loginEstudiante","idContactoEstudiantes","loginEstudiante","idPension","hibernateLazyInitializer" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Estudiante implements Serializable  {





	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	 @Basic(optional = false)
	 @Column(name = "id")
	 private String id;


	 private String Nombre;
	 private String ApellidoMaterno;
	 private String ApellidoPaterno;

	@Column(nullable=false , length=50)
	 private String correo;

	 @Basic(optional = false)
	    @Column(name = "numero_documento", unique = true)
	    private String numeroDocumento;
	  @JoinColumn(name = "id_sede", referencedColumnName = "id")
	  @ManyToOne(optional = false,fetch = FetchType.LAZY)

	    private Sede idSede;



	  @JoinColumn(name = "id_tipo_documento_identidad", referencedColumnName = "id")
	  @ManyToOne(optional = false,fetch = FetchType.LAZY)
	    private TipoDocumentoIdentidad idTipoDocumentoIdentidad;



	  @JoinColumn(name = "gradoEstudiante_id", referencedColumnName = "id")
	  @ManyToOne(optional = false,fetch = FetchType.LAZY)

	  private GradoEstudiante gradoEstudiante_id;


	   @JoinColumn(name = "id_file", referencedColumnName = "id", nullable = true)
	   @NotFound(action = NotFoundAction.IGNORE)
	   @Nullable()
	   @ManyToOne(optional = true,fetch = FetchType.LAZY)

			private File id_files;


	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "estadoEstudiante_id", referencedColumnName = "id")
	private Estado_Estudiante estadoEstudiante_id;


@OneToMany(cascade =CascadeType.ALL  ,mappedBy = "idEstudiante")
private List<ContactoEstudiantes> idContactoEstudiantes;

	@OneToMany(cascade =CascadeType.ALL  ,mappedBy = "estudiante_id")
	private List<NotificacionEstudiantes> notificacionEstudiantesList;

	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante_id")
	  private List<LoginEstudiantes> loginEstudiante;


	  @OneToMany(cascade =CascadeType.ALL  ,mappedBy = "idEstudiante")
	  List<Pension> idPension;

	    @Column(name = "direccion")
	    private String direccion;

	    @Column(name = "idTipoSexo")
	    private String idTipoSexo;

	    @Column(name = "gradoInstruccion")
	    private String gradoInstruccion;

	    @Transient
	    private String nombreCompleto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;




}
