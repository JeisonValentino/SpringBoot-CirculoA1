package com.ProyectoEmpresarial.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Sede;

@Repository
public interface Sede_Interface extends CrudRepository<Sede, Long> {

	public Sede findByNombre(String nombre);

}
