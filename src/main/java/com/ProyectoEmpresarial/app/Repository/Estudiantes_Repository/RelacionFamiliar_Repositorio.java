package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Relacionfamiliar;

@Repository
public interface RelacionFamiliar_Repositorio extends CrudRepository<Relacionfamiliar, Integer>{

}
