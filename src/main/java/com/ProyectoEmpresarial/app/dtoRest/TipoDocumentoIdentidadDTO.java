package com.ProyectoEmpresarial.app.dtoRest;

import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
@Data
public class TipoDocumentoIdentidadDTO {
    private Long id;


    private String nombreDocumento;

}
