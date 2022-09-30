package com.ProyectoEmpresarial.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoEmpresarial.app.Repository.Sede_Interface;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoFiltrado;
import com.ProyectoEmpresarial.app.dtoRest.SedeRest;
import com.ProyectoEmpresarial.app.entity.Sede;
import com.ProyectoEmpresarial.app.entity.Service.UsersEmpleadoServiceInterface;


@RestController
@RequestMapping("/sede")
public class SedeController {
	@Autowired
	Sede_Interface sedeInterface;
	@Autowired
	UsersEmpleadoServiceInterface  usersEmpleadoServiceInterface;
	@GetMapping("/listaSede")
	public List<Sede>  listarSedes () {

		List<Sede>  empleadoFiltradoEntity = usersEmpleadoServiceInterface.ListarSedes();

		return empleadoFiltradoEntity;

	}

	@GetMapping("/listaSedeReal")
	public List<SedeRest>listasedeReal () {

		List <SedeRest> sedeRestEntity = usersEmpleadoServiceInterface.listaSedesReal();
		return sedeRestEntity ;
	}

	@PostMapping("/crearSedes")
	public void CrearSedes(@RequestBody SedeRest sedeRest) {

		usersEmpleadoServiceInterface.CrearSedes(sedeRest);
	}

	@PostMapping("/editarSedes")
	public void EditarSede(@RequestBody SedeRest sedeRest) {
		usersEmpleadoServiceInterface.EditarSede(sedeRest);

	}


	@DeleteMapping(value="/{id}")
	public void EliminarSede(@PathVariable("id") Long id) {
		sedeInterface.deleteById(id);
	}

	@PostMapping("/EliminarListaSede")
	public void EliminarListaSede(@RequestBody List<SedeRest> sedeRest ) {

		for(SedeRest sede:sedeRest) {
			sedeInterface.deleteById(sede.getId());
		}

	}
}
