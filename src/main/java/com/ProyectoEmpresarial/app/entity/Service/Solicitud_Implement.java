package com.ProyectoEmpresarial.app.entity.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.Controller.Controller_Solicitud;
import com.ProyectoEmpresarial.app.Repository.Empleado_Interface;
import com.ProyectoEmpresarial.app.Repository.File_Interface;
import com.ProyectoEmpresarial.app.Repository.Interface_Solicitud;
import com.ProyectoEmpresarial.app.Repository.Post_Interface;
import com.ProyectoEmpresarial.app.Repository.Roles_Interface;
import com.ProyectoEmpresarial.app.Repository.User_Interface;
import com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository.Certificado_Interface;
import com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository.Matricula_Interface;
import com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository.Queja_Interface;
import com.ProyectoEmpresarial.app.Repository.MesaPartes_Repository.Traslado_Interface;
import com.ProyectoEmpresarial.app.dtoRest.PostAndSolicitud;
import com.ProyectoEmpresarial.app.dtoRest.PostRest;
import com.ProyectoEmpresarial.app.dtoRest.SolicitudDto;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Roles;
import com.ProyectoEmpresarial.app.entity.Solicitud;
import com.ProyectoEmpresarial.app.entity.Usuario;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Certificado;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Matricula;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Queja;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Tramite_Traslado;

@Service
public class Solicitud_Implement implements Solicitud_Interface{


	@Autowired
	Interface_Solicitud interfaceSolicitud;

	@Autowired
	Post_Interface postInterface;

	@Autowired
	Certificado_Interface certificadoInterface;

	@Autowired
	Matricula_Interface matriculaInterface;

	@Autowired
	Queja_Interface quejaInterface;

	@Autowired
	Traslado_Interface trasladoInterface;

	@Autowired
	File_Interface fileInterface;

	@Autowired
	Empleado_Interface empleadoInterface;


	@Autowired
	Roles_Interface roleInterface;

	@Autowired
	User_Interface userInterface;

	private Logger logger = LoggerFactory.getLogger(Controller_Solicitud.class);

	@Override
	public Solicitud GuardarSolicitud(SolicitudDto data,List<MultipartFile> files)throws Exception {


		Solicitud solicitudEntity =new Solicitud();
		Matricula matriculaEntity = new Matricula();
		Certificado certificadoEntity=new Certificado();
		Tramite_Traslado trasladoEntity=new Tramite_Traslado();
		Queja quejaEntity=new Queja();


		solicitudEntity.setNombre(data.getNombre());
		solicitudEntity.setApellidoMaterno(data.getApellidoMaterno());
		solicitudEntity.setApellidoPaterno(data.getApellidoPaterno());
		solicitudEntity.setCorreoPersonal(data.getCorreoPersonal());
		solicitudEntity.setTipoDni(data.getTipoDni());
		solicitudEntity.setNumeroDocumento(data.getNumeroDocumento());
		solicitudEntity.setDomicilio(data.getDomicilio());
		solicitudEntity.setId(data.getId());
		solicitudEntity.setTipoSolicitud(data.getTipoReclamo());
		logger.info(data.getTipoReclamo());
		interfaceSolicitud.save(solicitudEntity);

		if(data.getTipoReclamo().equals("QUEJA") ) {
			quejaEntity.setDetalle(data.getDetalle());
			quejaEntity.setPedido(data.getPedido());
			quejaEntity.setId(data.getId());
			quejaEntity.setIdSolicitud(solicitudEntity);
			quejaInterface.save(quejaEntity);

		}else if(data.getTipoReclamo().equals("CERTIFICADOS")) {
			certificadoEntity.setNombreEstudiante(data.getNombreEstudiante());
			certificadoEntity.setApellidoMaternoEstudiante(data.getApellidoMaternoEstudiante());
			certificadoEntity.setApellidoPaternoEstudiante(data.getApellidoPaternoEstudiante());
			certificadoEntity.setNumeroDocumentoEstudiante(data.getNumeroDocumentoEstudiante());
			certificadoEntity.setTipoDocumentoEstudiante(data.getTipoDocumentoEstudiante());
			certificadoEntity.setIdSolicitud(solicitudEntity);
			certificadoEntity.setId(data.getId());
			certificadoInterface.save(certificadoEntity);
logger.info("llego al certificado");
		}else if(data.getTipoReclamo().equals("TRAMITE DE TRASLADO")) {
			trasladoEntity.setNombreEstudiante(data.getNombreEstudiante());
			trasladoEntity.setApellidoMaternoEstudiante(data.getApellidoMaternoEstudiante());
			trasladoEntity.setApellidoPaternoEstudiante(data.getApellidoPaternoEstudiante());
			trasladoEntity.setNumeroDocumentoEstudiante(data.getNumeroDocumentoEstudiante());
			trasladoEntity.setTipoDocumentoEstudiante(data.getTipoDocumentoEstudiante());

			trasladoEntity.setIdSolicitud(solicitudEntity);
			trasladoInterface.save(trasladoEntity);

		}else if(data.getTipoReclamo().equals("MATRICULA")) {
			logger.info("PASO MATRICULA");
			matriculaEntity.setNombreEstudiante(data.getNombreEstudiante());
			matriculaEntity.setApellidoMaternoEstudiante(data.getApellidoMaternoEstudiante());
			matriculaEntity.setApellidoPaternoEstudiante(data.getApellidoPaternoEstudiante());
			matriculaEntity.setNumeroDocumentoEstudiante(data.getNumeroDocumentoEstudiante());
			matriculaEntity.setTipoDocumentoEstudiante(data.getTipoDocumentoEstudiante());
			matriculaEntity.setTipoMatricula(data.getTipoMatricula());
			matriculaEntity.setIdSolicitud(solicitudEntity);
			matriculaEntity.setId(data.getId());
			matriculaInterface.save(matriculaEntity);

		}

		for(int i = 0 ; i<files.size() ; i++) {
			File fileEntity=new File();
			fileEntity.setData(files.get(i).getBytes());
			fileEntity.setName(files.get(i).getOriginalFilename());
			fileEntity.setType(files.get(i).getContentType());

			if(data.getTipoReclamo().equals("MATRICULA")) {
			fileEntity.setIdMatricula(matriculaEntity);
			}else
			if(data.getTipoReclamo().equals("TRAMITE DE TRASLADO")) {
			fileEntity.setIdTraslado(trasladoEntity);
			}else
			if(data.getTipoReclamo().equals("CERTIFICADOS")) {
			fileEntity.setIdCertificado(certificadoEntity);
			}else
			if(data.getTipoReclamo().equals("QUEJA")) {
			fileEntity.setIdQueja(quejaEntity);
			}
			fileInterface.save(fileEntity);
		}


		return solicitudEntity;
	}

	@Override
	public PostAndSolicitud consultaBasicaPost(String id) {

		PostAndSolicitud  posSoliEntity=new PostAndSolicitud();
		Solicitud solicitudEntity= interfaceSolicitud.findById(id).get();
		List<Post> postEntity = postInterface.post(id);
		List<PostRest>postRest = new ArrayList<PostRest>();




		posSoliEntity.setId(solicitudEntity.getId());
		posSoliEntity.setNombreCompleto(solicitudEntity.getNombre()+""+solicitudEntity.getApellidoMaterno()+""+solicitudEntity.getApellidoPaterno());
		posSoliEntity.setCorreoPersonal(solicitudEntity.getCorreoPersonal());
		posSoliEntity.setNumeroDocumento(solicitudEntity.getNumeroDocumento());



		for(int i = 0 ; i<postEntity.size();i++) {
			Usuario userEntity = new Usuario();
			Roles roleEntity = new Roles();
			 userEntity =  userInterface.findByIdEmpleado(postEntity.get(i).getIdEmpleado());
			 roleEntity = roleInterface.buscarRoleIdUser(userEntity.getId());
			PostRest postRestEntity = new PostRest();
			postRestEntity.setId(postEntity.get(i).getId());
			postRestEntity.setCargo(roleEntity.getConcepto());
			postRestEntity.setConcepto(postEntity.get(i).getConcepto());
			postRestEntity.setMensaje(postEntity.get(i).getMensaje());
			postRestEntity.setTitulo(postEntity.get(i).getTitulo());
			postRestEntity.setNombreEmpleado(postEntity.get(i).getIdEmpleado().getNombre() + "" +postEntity.get(i).getIdEmpleado().getApellidoMaterno()+""+postEntity.get(i).getIdEmpleado().getApellidoPaterno());
			postRest.add(postRestEntity);

		}

			posSoliEntity.setPost(postRest);




		return posSoliEntity;
	}

	@Override
	public Post CrearPost(PostRest postRestEntity, List<MultipartFile> files) throws Exception {

		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String correo=authentication.getPrincipal().toString();

		Usuario useEntity = userInterface.findByCorreo(correo);
	Post postEntity =new Post();
	Solicitud soliEntity = interfaceSolicitud.findById(postRestEntity.getIdSolicitud()).get();
	postEntity.setConcepto(postRestEntity.getConcepto());
	postEntity.setIdEmpleado(useEntity.getIdEmpleado());
	postEntity.setMensaje(postRestEntity.getMensaje());
	postEntity.setTitulo(postRestEntity.getTitulo());
	logger.info(soliEntity.getId() + "");
	postEntity.setIdSolicitud(soliEntity);

		logger.info("paso2");

			postInterface.save(postEntity);

			logger.info(postEntity.getId()+"");
			if(files != null) {

			for(int i = 0 ; i<files.size() ; i++) {
				File fileEntity=new File();
				fileEntity.setData(files.get(i).getBytes());
				fileEntity.setName(files.get(i).getOriginalFilename());
				fileEntity.setType(files.get(i).getContentType());
				fileEntity.setIdPost(postEntity);
				logger.info("paso4 ");
				fileInterface.save(fileEntity);
				logger.info("paso5 ");
			}
			}
	logger.info("paso3 ");


		return postEntity;
	}

	@Override
	public List<PostRest> listarPost() {



		return null;
	}

	@Override
	@Transactional()
	public List<PostAndSolicitud> listarTodoSolicitud() {

		List<PostAndSolicitud>  posSoliEntityList=new ArrayList<PostAndSolicitud>();
		List<Solicitud> solicitudEntity= (List<Solicitud>)interfaceSolicitud.findAll();





		for(int a = 0;a<solicitudEntity.size(); a++ ) {
			PostAndSolicitud  posSoliEntity=new PostAndSolicitud();
		posSoliEntity.setId(solicitudEntity.get(a).getId());
		posSoliEntity.setNombreCompleto(solicitudEntity.get(a).getNombre()+" "+solicitudEntity.get(a).getApellidoMaterno()+" "+solicitudEntity.get(a).getApellidoPaterno());
		posSoliEntity.setCorreoPersonal(solicitudEntity.get(a).getCorreoPersonal());
		posSoliEntity.setNumeroDocumento(solicitudEntity.get(a).getNumeroDocumento());
		posSoliEntity.setTipoReclamo(solicitudEntity.get(a).getTipoSolicitud());
		List<PostRest>postRest = new ArrayList<PostRest>();

		List<Post>  postEntity = postInterface.post(solicitudEntity.get(a).getId());

		for(int i = 0 ; i<postEntity.size();i++) {
			Usuario userEntity = new Usuario();
			Roles roleEntity = new Roles();
			 userEntity =  userInterface.findByIdEmpleado(postEntity.get(i).getIdEmpleado());
			 roleEntity = roleInterface.buscarRoleIdUser(userEntity.getId());
			PostRest postRestEntity = new PostRest();

			postRestEntity.setId(postEntity.get(i).getId());
			postRestEntity.setCargo(roleEntity.getConcepto());
			postRestEntity.setConcepto(postEntity.get(i).getConcepto());
			postRestEntity.setMensaje(postEntity.get(i).getMensaje());
			postRestEntity.setTitulo(postEntity.get(i).getTitulo());

			postRestEntity.setNombreEmpleado(postEntity.get(i).getIdEmpleado().getNombre() + " " +postEntity.get(i).getIdEmpleado().getApellidoMaterno()+" "+postEntity.get(i).getIdEmpleado().getApellidoPaterno());

			List<byte[]> fileEntityList= new ArrayList<>();

			List<File> fileEntity=(List<File>)fileInterface.obtenerArchivosPost(postEntity.get(i).getId());

			logger.info("paso imagen antes "+ fileEntity.size());
			for(int r = 0  ; r<fileEntity.size();r++) {
				logger.info("paso imagen "+r);
				byte[] fileR = {};
				fileR=(fileEntity.get(r).getData());

				fileEntityList.add(fileR);
			}
			logger.info("paso imagen despues ");

			postRestEntity.setPhoto(fileEntityList);
			postRest.add(postRestEntity);

		}
		posSoliEntity.setPost(postRest);
		posSoliEntityList.add(posSoliEntity);
		}




		return posSoliEntityList;
	}

	@Override
	public PostAndSolicitud listarTodoPost(String id) {


		Solicitud solicitudEntity= interfaceSolicitud.findById(id).get();

		PostAndSolicitud  posSoliEntity=new PostAndSolicitud();
		posSoliEntity.setId(solicitudEntity.getId());
		posSoliEntity.setNombreCompleto(solicitudEntity.getNombre()+" "+solicitudEntity.getApellidoMaterno()+" "+solicitudEntity.getApellidoPaterno());
		posSoliEntity.setCorreoPersonal(solicitudEntity.getCorreoPersonal());
		posSoliEntity.setNumeroDocumento(solicitudEntity.getNumeroDocumento());
		posSoliEntity.setTipoReclamo(solicitudEntity.getTipoSolicitud());
		List<PostRest>postRest = new ArrayList<PostRest>();

		List<Post>  postEntity = postInterface.post(solicitudEntity.getId());

		for(int i = 0 ; i<postEntity.size();i++) {
			Usuario userEntity = new Usuario();
			Roles roleEntity = new Roles();
			 userEntity =  userInterface.findByIdEmpleado(postEntity.get(i).getIdEmpleado());
			 roleEntity = roleInterface.buscarRoleIdUser(userEntity.getId());
			PostRest postRestEntity = new PostRest();

			postRestEntity.setId(postEntity.get(i).getId());
			postRestEntity.setCargo(roleEntity.getConcepto());
			postRestEntity.setConcepto(postEntity.get(i).getConcepto());
			postRestEntity.setMensaje(postEntity.get(i).getMensaje());
			postRestEntity.setTitulo(postEntity.get(i).getTitulo());

			postRestEntity.setNombreEmpleado(postEntity.get(i).getIdEmpleado().getNombre() + " " +postEntity.get(i).getIdEmpleado().getApellidoMaterno()+" "+postEntity.get(i).getIdEmpleado().getApellidoPaterno());

			List<byte[]> fileEntityList= new ArrayList<>();

			List<File> fileEntity=(List<File>)fileInterface.obtenerArchivosPost(postEntity.get(i).getId());

			logger.info("paso imagen antes "+ fileEntity.size());
			for(int r = 0  ; r<fileEntity.size();r++) {
				logger.info("paso imagen "+r);
				byte[] fileR = {};
				fileR=(fileEntity.get(r).getData());

				fileEntityList.add(fileR);
			}
			logger.info("paso imagen despues ");

			postRestEntity.setPhoto(fileEntityList);
			postRest.add(postRestEntity);

		}
		posSoliEntity.setPost(postRest);
		return posSoliEntity;
	}





}
