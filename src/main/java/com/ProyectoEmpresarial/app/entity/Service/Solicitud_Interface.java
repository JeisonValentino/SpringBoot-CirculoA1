package com.ProyectoEmpresarial.app.entity.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.dtoRest.PostAndSolicitud;
import com.ProyectoEmpresarial.app.dtoRest.PostRest;
import com.ProyectoEmpresarial.app.dtoRest.SolicitudDto;
import com.ProyectoEmpresarial.app.entity.Solicitud;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;

public interface Solicitud_Interface  {


	public Solicitud GuardarSolicitud(SolicitudDto data, List<MultipartFile> files ) throws Exception;


	public PostAndSolicitud consultaBasicaPost(String id);


	public Post CrearPost (PostRest postRestEntity , List<MultipartFile> files)throws Exception;

	public List<PostRest> listarPost();

	public List<PostAndSolicitud> listarTodoSolicitud();

	public PostAndSolicitud listarTodoPost(String id);

}
