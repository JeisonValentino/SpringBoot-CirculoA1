package com.ProyectoEmpresarial.app.entity.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.dtoRest.EmpleadoDetailRequesModel;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoFiltrado;
import com.ProyectoEmpresarial.app.dtoRest.SedeRest;
import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Sede;



public interface UsersEmpleadoServiceInterface {
	public Empleado crearEmpleado(EmpleadoDetailRequesModel empleadoDetailRequestModel,MultipartFile file)throws Exception;

	public void modificarEmpleado (EmpleadoDetailRequesModel empleadoDetailRequesModel,MultipartFile file)throws Exception ;

	public List<EmpleadoFiltrado> empleadoFiltrado();

	public List<EmpleadoDetailRequesModel> ListaEmpleados();

	public List<Sede>  ListarSedes();

	public List<SedeRest> listaSedesReal();
	public Sede CrearSedes(SedeRest sedeRest) ;

	public Sede EditarSede(SedeRest sedeRest);

}
