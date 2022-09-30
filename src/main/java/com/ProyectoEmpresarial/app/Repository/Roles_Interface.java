package com.ProyectoEmpresarial.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Roles;

@Repository
public interface Roles_Interface extends CrudRepository<Roles,Long > {




	@Query("SELECT r FROM Roles r  JOIN usuario u ON u.id= r.user.id WHERE u.correo = :correo")
	public Roles buscadoroleUsers (@Param("correo") String correo);

	public Roles findByConcepto(String concepto);

	@Query("SELECT r FROM Roles r JOIN usuario u ON u.id=r.user WHERE u.id = :id")
	public Roles buscarRoleIdUser(@Param("id") Long id);
}
