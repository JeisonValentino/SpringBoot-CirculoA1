package com.ProyectoEmpresarial.app.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.Estudiante_Repository;
import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.LoginEstudiantes_Repository;
import com.ProyectoEmpresarial.app.dtoRest.Estudiantes.ContactoEstudiantesDTO;
import com.ProyectoEmpresarial.app.dtoRest.Estudiantes.EstudianteDTO;
import com.ProyectoEmpresarial.app.dtoRest.Estudiantes.LoginEstudiantesDTO;
import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.ContactoEstudiantes_Repository;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Service.EstudiantesServiceInterface;


@RestController
@RequestMapping("/estudiante")
public class Controller_Estudiante {


	private Logger logger = LoggerFactory.getLogger(Controller_Estudiante.class);

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EstudiantesServiceInterface estudianteServiceInterface;
	@Autowired
	private ContactoEstudiantes_Repository contactoEstudiantes_Repository;
	@Autowired
	private LoginEstudiantes_Repository loginEstudiantes_repository;
	@GetMapping(value="/ObtenerListaEstudiantes" )
	public ResponseEntity<?>  listarSolicitudT(){
		List<Estudiante> estudianteEntity = null;
		try {
				estudianteEntity = estudianteServiceInterface.listaDeEstudiantes();
		}catch (Response e) {
			return ResponseEntity
				    .status(HttpStatus.FORBIDDEN)
				    .body(new Response (e.getMessage()));
		}
		return new ResponseEntity<List<Estudiante>>(estudianteEntity,HttpStatus.OK);

	}

	@PostMapping(value="/GuardarEstudiante" , consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>  GuardarEstudiante(  @RequestBody  EstudianteDTO EstudianteString ) throws Exception{
logger.info(EstudianteString.getNombre() + "AQUI ESTA EL NOMBRE ESTUDIANTE");
		Estudiante estudianteResponse =new Estudiante();
		try {
			Estudiante estudiante= 	modelMapper.map(EstudianteString , Estudiante.class);
			logger.info(estudiante.getNumeroDocumento());
			estudiante.setId("C"+estudiante.getNumeroDocumento());
			 estudianteResponse=	estudianteServiceInterface.GuardarEstudiante(estudiante);

		}catch (Response e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body(new Response (e.getMessage()));
		}

		return new ResponseEntity<Estudiante>(estudianteResponse,HttpStatus.OK);
	}

	@PutMapping(value="/ModificarEstudiante")
public ResponseEntity<Response>  ModificarEstudiante( @RequestBody EstudianteDTO EstudianteString) throws Exception {


		try {
			Estudiante estudiante= 	modelMapper.map(EstudianteString , Estudiante.class);
			estudianteServiceInterface.ModificarEstudiante(estudiante);
		}catch (Response e) {
			return ResponseEntity
				    .status(HttpStatus.FORBIDDEN)
				    .body(new Response (e.getMessage()));
		}

		return  ResponseEntity.status(HttpStatus.OK)
			    .body(new Response ("Se modifico al estudiante correctamente "));
	}

	@DeleteMapping(value="/EliminarformaContacto/{id}")
	public ResponseEntity<Response > EliminarformaContacto(@PathVariable("id") String id)throws Exception{




		if(contactoEstudiantes_Repository.findById(id) ==(null)) throw new   Response("el contacto no existe");

		else {
		try {
			contactoEstudiantes_Repository.deleteById(id);
		}catch (Response e) {
			return ResponseEntity
				    .status(HttpStatus.FORBIDDEN)
				    .body(new Response (e.getMessage()));
		}


		return  ResponseEntity.status(HttpStatus.OK)
			    .body(new Response ("Se elimino al contacto correctamente "));
		}
	}

	@GetMapping(value="/BuscarListaformaContactoPorEstudiante/{id}")
	public ResponseEntity<?> BuscarListaformaContactoPorEstudiante(@PathVariable("id") String id){

		 List<ContactoEstudiantes> a;
		try {

		 a =(List<ContactoEstudiantes>)contactoEstudiantes_Repository.encontrarPorID(id);

		}catch (Response e) {
			return ResponseEntity
				    .status(HttpStatus.FORBIDDEN)
				    .body(new Response (e.getMessage()));
		}

		return new ResponseEntity<List<ContactoEstudiantes>>(a,HttpStatus.OK);
	}


	@GetMapping(value="/BuscarLoginEstudiantes/{id}")
	public ResponseEntity<?> BuscarLoginEstudiantes(@PathVariable("id") String id){
		LoginEstudiantes loginEstudiantes=new LoginEstudiantes();
		try {
			loginEstudiantes=loginEstudiantes_repository.obtenerUsuarioPorEstudiante(id);

			if(loginEstudiantes==null)throw new Response("no hay datos ");

	}catch (Response e) {
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(new Response (e.getMessage()));
	}
		return new ResponseEntity<LoginEstudiantes>(loginEstudiantes,HttpStatus.OK);
	}


	@PostMapping(value="/GuardarLoginEstudiantes")
	public ResponseEntity<Response>GuardarLoginEstudiantes(@RequestBody LoginEstudiantesDTO contactoEstudiantes) {

		LoginEstudiantes estudianteLogin = modelMapper.map(contactoEstudiantes, LoginEstudiantes.class);
logger.info(estudianteLogin.getCorreo()+"aqui esta el correo");

		logger.info("empieza login estudiante");
try{


	estudianteServiceInterface.GuardarLoginEstudiantes(estudianteLogin);

	}catch (Response e) {
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(new Response (e.getMessage()));
	}
		return  ResponseEntity.status(HttpStatus.OK)
				.body(new Response ("Se Guardo al contacto correctamente "));
	}

	@PostMapping(value="/GuardarformaContactoPorEstudiante/{id}")
	public ResponseEntity<Response >GuardarformaContactoPorEstudiante(@RequestBody List<ContactoEstudiantesDTO> contactoEstudiantes,@PathVariable("id") String id){
logger.info(id+"aqui esta el id ");

List<ContactoEstudiantes> listaContactosporId = contactoEstudiantes_Repository.encontrarPorID(id);
		List<ContactoEstudiantes> contactoEstudiantes1 = contactoEstudiantes_Repository.encontrarPorID(id);



logger.info("antes del for ");
			for (ContactoEstudiantes contactoEstudiantes2 : listaContactosporId) {

				for (ContactoEstudiantesDTO estudiante : contactoEstudiantes) {

					if(estudiante.getId() != null) {
				if (contactoEstudiantes2.getId().equals(estudiante.getId())) {
					logger.info(contactoEstudiantes2.getId()+"paso un id");
					contactoEstudiantes1.remove(contactoEstudiantes2);


				}
			}
		}
	}

		try {
			for (ContactoEstudiantes contactoEstudiantes3 : contactoEstudiantes1) {
				contactoEstudiantes_Repository.deleteById(contactoEstudiantes3.getId());
			}

			logger.info("despues del null ");




			for (ContactoEstudiantesDTO estudiante : contactoEstudiantes) {
logger.info("antes del id  save"+(estudiante.getId() ==null));
				if(!(estudiante.getId() ==null)) {
					logger.info("despues  del id  save");
					ContactoEstudiantes estudianteContacto = modelMapper.map(estudiante, ContactoEstudiantes.class);
					contactoEstudiantes_Repository.save(estudianteContacto);
				}else{
					logger.info("no hay   del id  save");
					UUID uuid = UUID.randomUUID();
					ContactoEstudiantes estudianteContacto = modelMapper.map(estudiante, ContactoEstudiantes.class);
					logger.info("despues del mapper ");
					estudianteContacto.setId(estudiante.getNumeroDocumento() + "Contacto" + id + uuid);
					contactoEstudiantes_Repository.save(estudianteContacto);
					logger.info("antes del save ");
					logger.info("antes del null 4 ");

				}
			}


			} catch(Response e){
				return ResponseEntity
						.status(HttpStatus.FORBIDDEN)
						.body(new Response(e.getMessage()));
			}

		return  ResponseEntity.status(HttpStatus.OK)
			    .body(new Response ("Se Guardo al contacto correctamente "));
	}


}
