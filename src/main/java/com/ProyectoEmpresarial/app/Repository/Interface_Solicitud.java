package com.ProyectoEmpresarial.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Solicitud;


@Repository
public interface Interface_Solicitud extends CrudRepository<Solicitud, String> {

}
