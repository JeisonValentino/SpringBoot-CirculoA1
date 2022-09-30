package com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Tramite_Traslado;


@Repository
public interface Traslado_Interface extends CrudRepository<Tramite_Traslado, String> {

}
