package com.ProyectoEmpresarial.app.entity.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Roles;
import com.ProyectoEmpresarial.app.entity.Usuario;
import com.ProyectoEmpresarial.app.Excepciones.ExcepcionesCorreo;
import com.ProyectoEmpresarial.app.Repository.Empleado_Interface;
import com.ProyectoEmpresarial.app.Repository.File_Interface;
import com.ProyectoEmpresarial.app.Repository.Roles_Interface;
import com.ProyectoEmpresarial.app.Repository.Sede_Interface;
import com.ProyectoEmpresarial.app.Repository.TipoDocumentoIdentidad_Interface;
import com.ProyectoEmpresarial.app.Repository.User_Interface;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.dtoRest.UserDetailRequestModel;
import com.ProyectoEmpresarial.app.dtoRest.UserRest;


@Service
public class UserServiceImplement implements UsersServiceInteface,UserDetailsService {


	@Autowired
	User_Interface userInterfaces;

	@Autowired
	Empleado_Interface empleadoInterfaces;


	@Autowired
	Sede_Interface sedeInterface;

	@Autowired
	TipoDocumentoIdentidad_Interface tipoDocumentoInterface;


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	Roles_Interface roleInterface;

	@Autowired
	File_Interface fileInterface;

	private Logger logger=LoggerFactory.getLogger(UserServiceImplement.class);


	@Override
	public void modificarUser(UserDetailRequestModel user) {

Usuario userEntity =new Usuario();

BeanUtils.copyProperties(user, userEntity);


Usuario usuarioEntity= userInterfaces.findById(user.getId()).get();

if((userInterfaces.findByCorreo(user.getCorreo())!=null) &&

		!((userInterfaces.findById(user.getId()).get().getCorreo()).equals(user.getCorreo()))) throw new  Response("El correo ya existe" );
try {
userEntity.setIdEmpleado(usuarioEntity.getIdEmpleado());

userEntity.setUserId(user.getUserId());
userEntity.setId(user.getId());
logger.info("un poquit ROLE"+user.getRole());
Usuario userEn =userInterfaces.save(userEntity);
Roles role =new Roles();
role.setConcepto(user.getRole());
role.setId(user.getIdRole());
role.setUser(userEn);
roleInterface.save(role);
}catch(Exception e)
{
	throw new Response("Ocurrio un error al Modificar el empleado, Ponerse en contacto con el administrador del sistema" + e.getMessage());
	}
	}

	@Override
	@Transactional
	public Usuario createUser(UserDetailRequestModel user) {

		if(userInterfaces.findByCorreo(user.getCorreo()) != null)
			throw new ExcepcionesCorreo("El correo ya existe");

		Usuario userEntity =new Usuario();
		try {

BeanUtils.copyProperties(user, userEntity);
logger.info("paso aqui primero"+user.getIdEmpleado());
Empleado empleado=  empleadoInterfaces.findById(user.getIdEmpleado()).get();
logger.info("un poquit empleado"+empleado.getId());


userEntity.setIdEmpleado(empleado);



UUID userID=UUID.randomUUID();
userEntity.setUserId(userID.toString());

logger.info("un poquit ROLE"+user.getRole());
Usuario userEn =userInterfaces.save(userEntity);

Roles role =new Roles();
logger.info("aqui esta el ROLE");
role.setConcepto(user.getRole());
logger.info("aqui esta el ROLE2"+userEn.getId());
role.setUser(userEn);
logger.info("AQUI l ROLE");
roleInterface.save(role);
logger.info("DESPUES DE el ROLE");

	}catch(Exception e)
	{
		throw new Response("Ocurrio un error al Modificar el empleado, Ponerse en contacto con el administrador del sistema" + e.getMessage());
		}


		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usersEntity =  userInterfaces.findByCorreo(correo);

		logger.info(correo);
		if(usersEntity == null ) {
			logger.error("EL CORREO NO EXISTE "+correo);
			throw new UsernameNotFoundException("el usuario "+correo+"no existe");
		}

		logger.info(""+usersEntity.getContraseña() +"contra");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	    String encodedPassword = new BCryptPasswordEncoder().encode(usersEntity.getContraseña());


for(Roles role :usersEntity.getRoles() ) {


			logger.info("ROLEA".concat(role.getConcepto()));



			authorities.add(new SimpleGrantedAuthority(role.getConcepto()));
		}
		if(authorities.isEmpty()) {
			logger.info("ERROR LOGIN : NO EXISTE EL USUARIO"+correo+" CON EL ROL  ");
			throw new UsernameNotFoundException("USUARIO o CORREO "+correo+"NO EXISTE "
					+ "EN EL SISTEMA ");
		}
			logger.info(authorities+"aqui esta el rol");

		return new  org.springframework.security.core.userdetails.User(usersEntity.getCorreo(),encodedPassword,authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario getUser(String correo) {
		logger.info("comenzar "+correo);


		Usuario usersEntity = userInterfaces.findByCorreo(correo);

		logger.info("antes del get user");

		if(usersEntity== null) {
			throw new UsernameNotFoundException(correo);
		}

		logger.info("despues del get user r"+usersEntity.getCorreo());
		return usersEntity;
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserRest> ListaUser() {
		Usuario usuarioEntity =new Usuario ();
		List<Usuario> userEntityList=  (List<Usuario>) userInterfaces.findAll();
		ArrayList<UserRest> userEntityRestList= new ArrayList<UserRest>();







		for(int i = 0 ;i<userEntityList.size();i++  ) {

			UserRest retornoUser = new UserRest();
			retornoUser.setCorreo(userEntityList.get(i).getCorreo());

			retornoUser.setId(userEntityList.get(i).getId());
retornoUser.setUserId(userEntityList.get(i).getUserId());
retornoUser.setFechaRegistro(userEntityList.get(i).getFechaRegistro());
retornoUser.setContraseña(userEntityList.get(i).getContraseña());
retornoUser.setRole(roleInterface.buscarRoleIdUser(userEntityList.get(i).getId()).getConcepto());
retornoUser.setIdRole(roleInterface.buscarRoleIdUser(userEntityList.get(i).getId()).getId());
retornoUser.setIdEmpleado(userEntityList.get(i).getIdEmpleado().getId());
retornoUser.setNumeroDocumento(userEntityList.get(i).getIdEmpleado().getNumeroDocumento());
retornoUser.setIdTipoDocumentoIdentidad(userEntityList.get(i).getIdEmpleado().getIdTipoDocumentoIdentidad().getId());
			userEntityRestList.add(retornoUser);




			}



		return userEntityRestList;
	}

	@Override
	@Transactional
	public UserRest GuardarUserEmpleado(UserRest userRest)throws Exception  {

logger.info("inicio");


		Usuario usuario =new Usuario();
		Empleado empleado =new Empleado();

		usuario.setContraseña(userRest.getContraseña());

		usuario.setUserId(userRest.getUserId());
		logger.info("paso aqui el  user 1  ");

		usuario.setId(userRest.getId());
		empleado.setId(empleadoInterfaces.findById(userRest.getIdEmpleado()).get().getId());
		logger.info("paso aqui el user 2 ");
		usuario.setCorreo(userRest.getCorreo());
		empleado.setNombre(userRest.getNombre());
		empleado.setApellidoPaterno(userRest.getApellidoPaterno());
		logger.info("paso aqui el user 3 ");

		empleado.setApellidoMaterno(userRest.getApellidoMaterno());
		empleado.setConocimientoInformatico(userRest.getConocimientoInformatico());
		logger.info("paso aqui el  user 4");

		empleado.setEstadoCivil(userRest.getEstadoCivil());
		empleado.setGradoInstruccion(userRest.getGradoInstruccion());
		empleado.setDireccion(userRest.getDireccion());
		logger.info("paso aqui el user 5 ");
		logger.info("paso aqui el user sede  "+userRest.getId());
		empleado.setIdSede(sedeInterface.findById(userRest.getIdSede()).get());
		logger.info("paso aqui el user 6 ");


		logger.info("paso aqui el user 7 ");

		empleado.setIdTipoDocumentoIdentidad(tipoDocumentoInterface.findById(userRest.getIdTipoDocumentoIdentidad()).get());
		logger.info("paso aqui el user 8 ");


logger.info("paso aqui el empleado user 6 ");

empleado.setNumeroDocumento(userRest.getNumeroDocumento());

empleado.setTelefono(userRest.getTelefono());
		usuario.setIdEmpleado(empleadoInterfaces.findById(userRest.getIdEmpleado()).get());


		Empleado empleadoEntity= empleadoInterfaces.findById(userRest.getIdEmpleado()).get();

		File fileEntity= modelMapper.map(userRest.getPhoto() , File.class);


		logger.info("paso aqui el empleado user inicio "+empleadoEntity.getId_files().getId());


		empleado.setId_files(fileInterface.save(fileEntity));

		logger.info("paso aqui el empleado user ");
		empleadoInterfaces.save(empleado);
		logger.info("paso aqui el empleado user 2  ");

		userInterfaces.save(usuario);
		logger.info("paso aqui el empleado user 3");


		return userRest;
	}




}
