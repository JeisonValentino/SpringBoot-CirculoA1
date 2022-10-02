package com.ProyectoEmpresarial.app.dtoRest.Estudiantes;

import lombok.Data;

import java.util.Date;

@Data
public class TipoNotificacionDto {


    private Integer id;

    private String concepto;

    private String Descripcion;

    private Date fechaCreacion;
}
