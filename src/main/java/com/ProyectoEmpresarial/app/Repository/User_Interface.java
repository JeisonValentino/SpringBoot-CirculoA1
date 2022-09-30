package com.ProyectoEmpresarial.app.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Usuario;

public interface User_Interface extends CrudRepository<Usuario , Long>  {



	Usuario findByCorreo(String correo);


	Usuario  findByIdEmpleado(Empleado idEmpleado);

}
