package com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Certificado;



@Repository
public interface Certificado_Interface extends CrudRepository<Certificado, String> {

}
