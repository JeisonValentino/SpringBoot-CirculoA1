package com.ProyectoEmpresarial.app.dtoRest.Estudiantes;

import com.ProyectoEmpresarial.app.dtoRest.FileDto;
import com.ProyectoEmpresarial.app.dtoRest.SedeRest;
import com.ProyectoEmpresarial.app.dtoRest.TipoDocumentoIdentidadDTO;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.GradoEstudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Pension;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Sede;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
public class EstudianteDTO {

    private String id;
private String Nombre;
    private String ApellidoMaterno;
    private String ApellidoPaterno;
    private String correo;
    private String numeroDocumento;
    private SedeRest idSede;
    private TipoDocumentoIdentidadDTO idTipoDocumentoIdentidad;
    private GradoEstudianteDTO gradoEstudiante_id;
    private EstadoEstudianteDTO estadoEstudiante_id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "idTipoSexo")
    private String idTipoSexo;
    @Column(name = "gradoInstruccion")
    private String gradoInstruccion;
    @Transient
    private String nombreCompleto;

}
