package com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Queja;

@Repository
public interface Queja_Interface extends CrudRepository<Queja, String> {

}
