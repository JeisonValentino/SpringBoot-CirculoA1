package com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Matricula;

@Repository
public interface Matricula_Interface extends CrudRepository<Matricula, String>{

}
