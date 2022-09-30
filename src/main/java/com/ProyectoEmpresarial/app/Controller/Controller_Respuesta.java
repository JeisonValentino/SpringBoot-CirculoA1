package com.ProyectoEmpresarial.app.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.dtoRest.PostAndSolicitud;
import com.ProyectoEmpresarial.app.dtoRest.PostRest;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.entity.Service.Solicitud_Interface;

@RestController
@RequestMapping("/respuesta")
public class Controller_Respuesta {
	private Logger logger = LoggerFactory.getLogger(Controller_Solicitud.class);
	@Autowired
	Solicitud_Interface solicitudInterface;


	@PostMapping(value="/CrearPost" , consumes = { MediaType.MULTIPART_FORM_DATA_VALUE ,
			MediaType.APPLICATION_OCTET_STREAM_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Response> guardarRespuesta (@RequestPart("obj")PostRest postRest ,@RequestPart("files") @RequestParam( required = false) List<MultipartFile> listaFotos) throws Exception{
	logger.info("paso"+postRest.getConcepto());
		solicitudInterface.CrearPost(postRest, listaFotos);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response("Los archivos fueron cargados correctamente al servidor"));
	}


	@GetMapping("/ObtenerSolicitud")
	public List<PostAndSolicitud> listarSolicitudT(){

		return solicitudInterface.listarTodoSolicitud();
	}


	@GetMapping("/ObtenerSolicitudPost/{id}")
	public PostAndSolicitud ObtenerSolicitudPost(@PathVariable String id){

		return solicitudInterface.listarTodoPost(id);
	}


}
