package com.ProyectoEmpresarial.app.dtoRest;

import lombok.Data;

@Data
public class EmpleadoDetailRequesModel {
	private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreTotal;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private String idEstado;
    private String idTipoDocumentoIdentidad;
    private SedeRest idSede;
    private String direccion;
    private String estadoCivil;
    private String gradoInstruccion;
    private String conocimientoInformatico;
    private  byte[] photo;








}
