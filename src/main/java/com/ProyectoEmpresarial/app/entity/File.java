package com.ProyectoEmpresarial.app.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Certificado;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Matricula;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Queja;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Tramite_Traslado;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "files")
@Data
@JsonIgnoreProperties({"idEmpleado","idEstudiante"})
public class File {
	 @Id
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	 @Column(name = "id", updatable = false, nullable = false)
	  private String id;
	  private String name;
	  private String type;

	  @Lob
	  private byte[]   data;


	  public File() {
	  }
	  public File(String name, String type, byte[]   data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }


	  @JoinColumn(name = "id_Certificado", referencedColumnName = "id")
	  @ManyToOne(optional = true,fetch = FetchType.LAZY)
	  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	  private Certificado idCertificado;

	  @JoinColumn(name = "id_Matricula", referencedColumnName = "id")
	  @ManyToOne(optional = true,fetch = FetchType.LAZY)
	  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	  private Matricula idMatricula;

	  @JoinColumn(name = "id_Queja", referencedColumnName = "id")
	  @ManyToOne(optional = true,fetch = FetchType.LAZY)
	  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	  private Queja idQueja;

	  @JoinColumn(name = "id_Traslado", referencedColumnName = "id")
	  @ManyToOne(optional = true,fetch = FetchType.LAZY)
	  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	  private Tramite_Traslado idTraslado;

	  @JoinColumn(name = "id_Post", referencedColumnName = "id")
	  @ManyToOne(optional = true,fetch = FetchType.LAZY)
	  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	  private Post idPost;



		 @OneToMany(cascade =CascadeType.ALL  ,mappedBy = "id_files")
		  private List<Empleado> idEmpleado;


		 @OneToMany(cascade =CascadeType.ALL  ,mappedBy = "id_files")
		  private List<Estudiante> idEstudiante;

	public List<Empleado> getIdEmpleado() {
			return idEmpleado;
		}
		public void setIdEmpleado(List<Empleado> idEmpleado) {
			this.idEmpleado = idEmpleado;
		}
	public Post getIdPost() {
		return idPost;
	}
	public void setIdPost(Post idPost) {
		this.idPost = idPost;
	}
	public Certificado getIdCertificado() {
		return idCertificado;
	}
	public void setIdCertificado(Certificado idCertificado) {
		this.idCertificado = idCertificado;
	}
	public Matricula getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Matricula idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Queja getIdQueja() {
		return idQueja;
	}
	public void setIdQueja(Queja idQueja) {
		this.idQueja = idQueja;
	}
	public Tramite_Traslado getIdTraslado() {
		return idTraslado;
	}
	public void setIdTraslado(Tramite_Traslado idTraslado) {
		this.idTraslado = idTraslado;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		return Objects.equals(id, other.id);
	}



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
	    return id;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public String getType() {
	    return type;
	  }
	  public void setType(String type) {
	    this.type = type;
	  }
	  public byte[]   getData() {
	    return data;
	  }
	  public void setData(byte[]   data) {
	    this.data = data;
	  }
}
