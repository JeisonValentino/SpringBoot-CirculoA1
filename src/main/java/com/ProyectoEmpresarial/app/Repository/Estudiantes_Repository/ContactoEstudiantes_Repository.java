package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;

@Repository
public interface ContactoEstudiantes_Repository extends CrudRepository<ContactoEstudiantes, String> {

	@Query("SELECT C FROM ContactoEstudiantes C JOIN Estudiante E ON C.idEstudiante=E.id WHERE E.id = :id")
	public List<ContactoEstudiantes> encontrarPorID(@Param("id") String id );


}
