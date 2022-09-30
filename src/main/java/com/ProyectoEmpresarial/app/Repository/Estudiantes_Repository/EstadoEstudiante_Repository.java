package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Estado_Estudiante;

@Repository
public interface EstadoEstudiante_Repository extends CrudRepository<Estado_Estudiante,Integer>{

}
