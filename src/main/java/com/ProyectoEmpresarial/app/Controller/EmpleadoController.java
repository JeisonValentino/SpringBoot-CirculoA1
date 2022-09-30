package com.ProyectoEmpresarial.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.Repository.Empleado_Interface;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoDetailRequesModel;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoFiltrado;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Service.UsersEmpleadoServiceInterface;
import com.ProyectoEmpresarial.app.entity.Service.UsersServiceInteface;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	UsersEmpleadoServiceInterface usersEmpleadoInterface;

	@Autowired
	Empleado_Interface empleadoInterface;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> crearEmpleado(@RequestPart("obj") EmpleadoDetailRequesModel empleadoDetailRequestModel,@RequestPart("files" )@RequestParam( required = false)MultipartFile file) throws Exception {

		try {
		 usersEmpleadoInterface.crearEmpleado(empleadoDetailRequestModel,file);
		}catch (Response e) {
		return	ResponseEntity
		    .status(HttpStatus.FORBIDDEN)
		    .body(new Response (e.getMessage()));
		}


		 return ResponseEntity
				    .status(HttpStatus.OK)
				    .body(new Response ("se creo al empleado satisfactoriamente"));
	}

	@PutMapping(value = "/modificarEmpleado",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  modificarEmpleado (@RequestPart("obj") EmpleadoDetailRequesModel empleadoDetailRequestModel, @RequestPart("files2" )@RequestParam( required = false) MultipartFile files2)throws Exception  {

		try {
		usersEmpleadoInterface.modificarEmpleado(empleadoDetailRequestModel,files2);
		}catch (Response e) {
			return	ResponseEntity
			    .status(HttpStatus.FORBIDDEN)
			    .body(new Response (e.getMessage()));
			}

		return ResponseEntity
			    .status(HttpStatus.OK)
			    .body(new Response ("Se modifico los datos del  empleado satisfactoriamente "));

	}

	@GetMapping("buscarId/{id}")
	public Empleado ObtenerEmpleadoId(Long id ) {
	Empleado empleadoEntity=empleadoInterface.findById(id).get();
		return empleadoEntity;
	}

	@GetMapping("buscarDni/{dni}")
	public Empleado ObtenerEmpleadoDNI(String dni ) {

		return empleadoInterface.findByNumeroDocumento(dni);
	}

	@GetMapping
	public List<EmpleadoDetailRequesModel>obtenerListaEmpleado(){

	List<EmpleadoDetailRequesModel>	entity=usersEmpleadoInterface.ListaEmpleados();
	return entity;
	}

	@DeleteMapping("/{id}")
	public void EliminarEmpleado(Long id) {

		empleadoInterface.deleteById(id);
	}


	@PostMapping("/EliminarListaEmpleados")
	public void eliminarListaEmpleado (@RequestBody List<EmpleadoDetailRequesModel> empleadoDetailRequestModel ) {

		for(EmpleadoDetailRequesModel empleado : empleadoDetailRequestModel ) {
			empleadoInterface.deleteById(empleado.getId());
		}

	}

	@GetMapping("/ListarEmpleadosNorole")
public List<EmpleadoFiltrado>	ObtenerFiltradoEmpleado(){

		List<EmpleadoFiltrado>empleadoEntity =usersEmpleadoInterface.empleadoFiltrado();
		return empleadoEntity;
	}


}
