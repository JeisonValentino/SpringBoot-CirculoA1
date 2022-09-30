package com.ProyectoEmpresarial.app.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ProyectoEmpresarial.app.entity.Empleado;

@Repository
public interface Empleado_Interface extends CrudRepository<Empleado,Long> {





	@Query("SELECT e FROM Empleado e JOIN usuario u ON e.id = u.idEmpleado.id WHERE u.correo =:correo")
	public Empleado buscarEmpleadoCorreo(@Param("correo")String correo);


	public Empleado findByNumeroDocumento(String NroDocumento);

}
