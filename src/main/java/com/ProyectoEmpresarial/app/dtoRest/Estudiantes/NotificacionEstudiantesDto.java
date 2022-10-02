package com.ProyectoEmpresarial.app.dtoRest.Estudiantes;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.TipoNotificacion;
import lombok.Data;

import java.util.Date;

@Data
public class NotificacionEstudiantesDto {


    private Integer id;

    private TipoNotificacionDto tipoNotificacion_id;

    private String Plataforma;

    private EstudianteDTO estudiante_id;

    private Date fechaCreacion;

    private String cronExpression;
    private String actionType;
    private String data;
}
