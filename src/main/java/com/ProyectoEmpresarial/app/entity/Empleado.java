package com.ProyectoEmpresarial.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author Stewen Jordi Mateo Villanueva <smateovj@gmail.com>
 */
@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Empleado implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "numero_documento", unique = true )

    private String numeroDocumento;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;


    @JoinColumn(name = "id_file", referencedColumnName = "id", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
		private File id_files;





	public void setId_files(File id_files) {
		this.id_files = id_files;
	}

	@JoinColumn(name = "id_tipo_documento_identidad", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TipoDocumentoIdentidad idTipoDocumentoIdentidad;

	@JsonBackReference(value="EmpleadoUsuario")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<Usuario> usuarioList;

    @JsonBackReference(value="solicitudEmpleados")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="idEmpleado", fetch=FetchType.LAZY)
	private List<Post>post;




    @JoinColumn(name = "id_sede", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sede idSede;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estadoCivil")
    private String estadoCivil;

    @Column(name = "gradoInstruccion")
    private String gradoInstruccion;



    @Column(name = "conocimientoInformatico")
    private String conocimientoInformatico;



    @Transient
    private String nombreCompleto;

    @Transient
    private int first;

    @Transient
    private int pageSize;








}
