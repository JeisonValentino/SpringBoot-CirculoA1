package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;

@Repository
public interface Estudiante_Repository extends CrudRepository<Estudiante,String> {



	public Estudiante findByNumeroDocumento (String numeroDocumento);
}
