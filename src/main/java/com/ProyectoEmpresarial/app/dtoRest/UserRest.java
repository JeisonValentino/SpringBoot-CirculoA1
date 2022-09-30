package com.ProyectoEmpresarial.app.dtoRest;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserRest {



	private Long id ;
private String userId;
private String correo;
private String contraseña;
private String Role;
private Long idRole;
private Date fechaRegistro;
private String encryptedPassword;
private String Estado;
private String action;
private String nombre;
private String apellidoPaterno;
private String apellidoMaterno;
private String numeroDocumento;
private String direccion;
private Long idTipoDocumentoIdentidad;
private String estadoCivil;
private String gradoInstruccion;
private String conocimientoInformatico;
private Long idEmpleado;
private Long idSede;
private String telefono;


private FileDto photo;




}
