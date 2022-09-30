	package com.ProyectoEmpresarial.app.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ProyectoEmpresarial.app.dtoRest.FileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.Roles;
import com.ProyectoEmpresarial.app.entity.Usuario;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Relacionfamiliar;
import com.ProyectoEmpresarial.app.entity.Service.UsersServiceInteface;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ProyectoEmpresarial.app.Repository.Empleado_Interface;
import com.ProyectoEmpresarial.app.Repository.File_Interface;
import com.ProyectoEmpresarial.app.Repository.Roles_Interface;
import com.ProyectoEmpresarial.app.Repository.User_Interface;
import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.RelacionFamiliar_Repositorio;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.dtoRest.UserDetailRequestModel;
import com.ProyectoEmpresarial.app.dtoRest.UserRest;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;

    @RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

@Autowired
UsersServiceInteface userService;

@Autowired
User_Interface userInterfaces;

@Autowired
Roles_Interface roleInterface;


@Autowired
Empleado_Interface empleadoInterface;

@Autowired
File_Interface fileInterface;
@Autowired
RelacionFamiliar_Repositorio relacionFamiliar_Repositorio;

private Logger logger=LoggerFactory.getLogger(UserController.class);


@Autowired
AuthenticationManager authenticationManager;



@PostMapping("/token/refresh")
public void refresh(HttpServletRequest request  ,HttpServletResponse response  )throws IOException  {

	String authorizationHeader=request.getHeader(AUTHORIZATION);

	if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	try {
	String refrest_Token=authorizationHeader.substring("Bearer ".length());

	Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
	JWTVerifier verifier =JWT.require(algorithm).build();
	DecodedJWT decodedJWT=verifier.verify(refrest_Token);
	String username=decodedJWT.getSubject();
	Usuario user= userService.getUser(username);

	String access_token=JWT.create()
			.withSubject(user.getCorreo())
			.withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
			.withIssuer(request.getRequestURL().toString()).withIssuer(request.getRequestURL().toString())
			.withClaim("roles", user.getRoles().stream().map(Roles::getConcepto)
					.collect(Collectors.toList())).sign(algorithm);

Map<String, String>tokens=new HashMap<>();
tokens.put("access_token", access_token);
tokens.put("refrest_Token", refrest_Token);
response.setContentType(MediaType.APPLICATION_JSON_VALUE);
new ObjectMapper().writeValue(response.getOutputStream(), tokens);



	}catch(Exception exception) {

		response.setHeader("error: ", exception.getMessage());
		response.setStatus(FORBIDDEN.value());
	Map<String, String>error=new HashMap<>();
	error.put("error_message", exception.getMessage());
	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	new ObjectMapper().writeValue(response.getOutputStream(), error);
	}


	}else {

		throw new RuntimeException("refresh token , no existe el token actual ");
	}


}





	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody UserDetailRequestModel userDetail) {
		try {
		userService.createUser(userDetail);


		}catch (Response e) {
			return ResponseEntity
		    .status(HttpStatus.FORBIDDEN)
		    .body(new Response (e.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Se creo al usuario satisfactoriamente"));
	}

	@PutMapping
	public ResponseEntity<Response>  modificarUser(@RequestBody UserDetailRequestModel userDetail) {
		try {
		userService.modificarUser(userDetail);

	}catch (Response e) {
		return ResponseEntity
	    .status(HttpStatus.FORBIDDEN)
	    .body(new Response (e.getMessage()));
	}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Se modifico al usuario satisfactoriamente "));


	}
	@GetMapping
	public UserRest GetUser() {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String correo=authentication.getPrincipal().toString();
		 logger.info(correo);
		 Usuario userdto=userService.getUser(correo);




		UserRest retornoUser =new UserRest();
		Roles role =new Roles();


		BeanUtils.copyProperties(userdto, retornoUser);


		Roles rolese=  roleInterface.buscadoroleUsers(correo);



		retornoUser.setRole(rolese.getConcepto());

		return retornoUser;
	}

	@GetMapping ("/Perfil")
	public UserRest GetUserPerfil() {

		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String correo=authentication.getPrincipal().toString();
		 logger.info(correo);
		 Usuario userdto=userService.getUser(correo);


		UserRest retornoUser =new UserRest();
		BeanUtils.copyProperties(userdto, retornoUser);

		Roles rolese=  roleInterface.buscadoroleUsers(correo);

		Empleado empleado=empleadoInterface.buscarEmpleadoCorreo(correo);

		retornoUser.setApellidoMaterno(empleado.getApellidoMaterno());
		retornoUser.setApellidoPaterno(empleado.getApellidoPaterno());
		retornoUser.setNombre(empleado.getNombre());
		retornoUser.setConocimientoInformatico(empleado.getConocimientoInformatico());
		retornoUser.setContraseña(userdto.getContraseña());
		retornoUser.setCorreo(userdto.getCorreo());
		retornoUser.setDireccion(empleado.getDireccion());


		retornoUser.setEstadoCivil(empleado.getEstadoCivil());
		retornoUser.setIdTipoDocumentoIdentidad(empleado.getIdTipoDocumentoIdentidad().getId());
		retornoUser.setRole(rolese.getConcepto());
		retornoUser.setNumeroDocumento(empleado.getNumeroDocumento());
		retornoUser.setGradoInstruccion(empleado.getGradoInstruccion());
		retornoUser.setIdSede(empleado.getIdSede().getId());
		retornoUser.setTelefono(empleado.getTelefono());
		retornoUser.setIdEmpleado(empleado.getId());


		try {

logger.info("amtes deñ filter");

			FileDto FILE =new FileDto();
			FILE.setId(empleado.getId_files().getId());
			FILE.setData(empleado.getId_files().getData());
			FILE.setName(empleado.getId_files().getName());
			FILE.setType(empleado.getId_files().getType());
		retornoUser.setPhoto(FILE);

		}catch (Exception e) {
		}
		return retornoUser;
	}





	@GetMapping ("/ListarUsuarios")
	public List<UserRest> GetListaUser() {

		 List<UserRest> userdto=userService.ListaUser();

		return userdto;
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response> eliminar (@PathVariable("id") Long id) {
		userInterfaces.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("El usuario fue eliminado"));
	}


	@PostMapping("/ListaUsuariosEliminar")
	public ResponseEntity<Response> eliminarLista(@RequestBody List<UserRest> UsuariosModels) {
		try {
		for(UserRest user:UsuariosModels) {
			userInterfaces.deleteById(user.getId());
		}

	}catch (Exception e) {
		return ResponseEntity
	    .status(HttpStatus.FORBIDDEN)
	    .body(new Response ("Ocurrio un error , contactarse con soporte del sistema "));
	}

		return ResponseEntity.status(HttpStatus.OK).body(new Response("Los usuarios fueron eliminado"));
	}



	@PutMapping(value = "/GuardarPorPerfil")
public ResponseEntity<Response> GuadarPorPerfil( @RequestBody UserRest userRest ) {

		try {

		userService.GuardarUserEmpleado(userRest);

	}catch (Exception e) {
		return ResponseEntity
	    .status(HttpStatus.FORBIDDEN)
	    .body(new Response ("Error el dni se repite con alguno de nuestros clientes , por favor revisar "));
	}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Los archivos fueron cargados correctamente al servidor"));
	}

	@GetMapping("/listaRelaconFamiliar")
	public ResponseEntity<?> listaFamiliaRelacion(){


		List<Relacionfamiliar>	estudianteEntity;
		try {
		estudianteEntity = (List<Relacionfamiliar>)relacionFamiliar_Repositorio.findAll();
	}catch (Response e) {
		return ResponseEntity
			    .status(HttpStatus.FORBIDDEN)
			    .body(new Response (e.getMessage()));
	}

		return new ResponseEntity<List<Relacionfamiliar>>(estudianteEntity,HttpStatus.OK);
	}



}
