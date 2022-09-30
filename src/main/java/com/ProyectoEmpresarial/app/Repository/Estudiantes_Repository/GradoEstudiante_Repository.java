package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Estudiantes.GradoEstudiante;


@Repository
public interface GradoEstudiante_Repository extends CrudRepository<GradoEstudiante,Integer>  {

}
