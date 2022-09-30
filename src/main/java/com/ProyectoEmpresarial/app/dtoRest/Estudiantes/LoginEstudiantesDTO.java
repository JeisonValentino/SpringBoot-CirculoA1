package com.ProyectoEmpresarial.app.dtoRest.Estudiantes;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Estado_Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
public class LoginEstudiantesDTO {


    private Integer id;


    private String correo;



    private Date fechaRegistro;


    private String contraseña;


    private EstudianteDTO estudiante_id;



}
