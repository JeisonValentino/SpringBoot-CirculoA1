package com.ProyectoEmpresarial.app.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.File;

@Repository
public interface File_Interface extends CrudRepository<File, String>{


	@Query("SELECT F FROM File F JOIN Post P ON F.idPost=P.id WHERE P.id = :id")
	public List<File> obtenerArchivosPost(@Param("id") Long id);

	@Query("SELECT F FROM File F JOIN Post P ON F.idPost=P.id WHERE P.id = :id")
	public File obtenerArchivosEmpleados(@Param("id") Long id);


}
