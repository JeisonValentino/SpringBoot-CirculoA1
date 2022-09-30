package com.ProyectoEmpresarial.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;

@Repository
public interface TipoDocumentoIdentidad_Interface extends CrudRepository<TipoDocumentoIdentidad, Long> {

	public TipoDocumentoIdentidad findByNombreDocumento(String nombreDocumento);

}
