package com.ProyectoEmpresarial.app.dtoRest.Estudiantes;

import com.ProyectoEmpresarial.app.dtoRest.TipoDocumentoIdentidadDTO;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Relacionfamiliar;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;
import lombok.Data;

import javax.persistence.Column;
@Data
public class ContactoEstudiantesDTO {

    private String id;

    @Column(name = "nombre")
    private String nombre ;

    private String apellidoMaterno;

    private String apellidoPaterno;


    private String numeroDocumento;
    private String numeroTelefono;
    private RelacionfamiliarDTO idrelacionfamiliar;
    private EstudianteDTO idEstudiante;

    private TipoDocumentoIdentidadDTO idTipoDocumentoIdentidad;
}
