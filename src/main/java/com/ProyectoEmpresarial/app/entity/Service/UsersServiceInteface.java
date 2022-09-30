package com.ProyectoEmpresarial.app.entity.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.dtoRest.UserDetailRequestModel;
import com.ProyectoEmpresarial.app.dtoRest.UserRest;
import com.ProyectoEmpresarial.app.entity.Usuario;


public interface UsersServiceInteface extends UserDetailsService {

	public Usuario createUser(UserDetailRequestModel user);

	public void modificarUser(UserDetailRequestModel user);
	public Usuario getUser(String correo);

public List<UserRest>ListaUser();

public UserRest GuardarUserEmpleado(UserRest userRest )throws Exception ;


}


