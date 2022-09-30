package com.ProyectoEmpresarial.app.entity.Service;

import java.util.List;

import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.LoginEstudiantes_Repository;
import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoEmpresarial.app.Repository.File_Interface;
import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.Estudiante_Repository;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Pension;



@Service
public class EstudiantesServiceImplement implements EstudiantesServiceInterface{

	private Logger log= LoggerFactory.getLogger(EstudiantesServiceImplement.class);

	@Autowired
	private Estudiante_Repository estudianteRepository;
	@Autowired
	private File_Interface fileInterface;

	@Autowired
	private LoginEstudiantes_Repository loginEstudiantes_repository;

	@Override
	public List<Estudiante> listaDeEstudiantes() {
		List<Estudiante> estudianteEntityList =null;

		try {
		 estudianteEntityList = (List<Estudiante> )estudianteRepository.findAll();

	}
	catch (Exception e) {
		throw new Response("Ocurrio un error al obtener la lista de estudiantes , ponerse en contacto con el desarrollador del sistema" + e.getMessage());
	}


		 if(estudianteEntityList.isEmpty()) throw new  Response("La lista esta vacia");
		return estudianteEntityList;
	}


	@Override
	public Estudiante GuardarEstudiante(Estudiante estudiante){
		Estudiante estudianteEntity = new Estudiante();
		File fileEntity=new File();

		log.info("despue de bean tuils");

		if((estudianteRepository.findByNumeroDocumento(estudiante.getNumeroDocumento())!=null))  throw new Response("el dni ya existe");

		try {
			estudianteEntity.setId("C"+estudiante.getNumeroDocumento());
			BeanUtils.copyProperties(estudiante, estudianteEntity);

		if( estudiante.getId_files()==null)  {
			log.info("antes de imagen");
			estudianteEntity.setId_files(fileInterface.save(fileEntity));

		}else {

			fileEntity.setData(estudiante.getId_files().getData());
			fileEntity.setName(estudiante.getId_files().getName());
			fileEntity.setType(estudiante.getId_files().getType());
			estudianteEntity.setId_files(fileInterface.save(fileEntity));
		}


		}catch (Exception e) {

			throw new Response("Ocurrio un error al intentar guardar el estudiante , ponerse en contacto con el desarrollador del sistema . log : " + e.getMessage());
		}

		log.info("despues de imagen");


		log.info("despues de imagen");
		return estudianteRepository.save(estudianteEntity);

	}

	@Override
	public void ModificarEstudiante(Estudiante estudiante) {
		Estudiante estudianteEntity= new Estudiante();


if((estudianteRepository.findByNumeroDocumento(estudiante.getNumeroDocumento())!=null) &&

				!((estudianteRepository.findById(estudiante.getId()).get().getNumeroDocumento()).equals(estudiante.getNumeroDocumento()))) throw new  Response(" El dni ya existe" );



		try {
			File fileEntity=new File();
			BeanUtils.copyProperties(estudiante, estudianteEntity);





			if(estudiante.getId_files()!=null) {

				fileEntity.setData(estudiante.getId_files().getData());
				fileEntity.setName(estudiante.getId_files().getName());
				fileEntity.setType(estudiante.getId_files().getType());
				fileEntity.setId(estudianteRepository.findById(estudiante.getId()).get().getId_files().getId());
				estudianteEntity.setId_files(fileInterface.save(fileEntity));

			}else {

				File fileEntityCarga=estudianteRepository.findById(estudiante.getId()).get().getId_files();

				fileEntity.setData(fileEntityCarga.getData());
				fileEntity.setName(fileEntityCarga.getName());
				fileEntity.setType(fileEntityCarga.getType());
				fileEntity.setId(estudianteRepository.findById(estudiante.getId()).get().getId_files().getId());
				estudianteEntity.setId_files(fileInterface.save(fileEntity));

			}







		}
		catch (Exception e) {
			throw new Response("Ocurrio un error al intentar guardar el estudiante , ponerse en contacto con el desarrollador del sistema . log : " + e.getMessage());
		}

		estudianteRepository.save(estudianteEntity);
	}

	@Override
	public List<ContactoEstudiantes> listaContactoporEstudiante(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void GuardarListaContactoporEstudiante(List<ContactoEstudiantes> listaContactoEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pension> listadePensionesPorEstudiantes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void GuardarListadePensionesPorEstudiantes(List<Pension> pensionesLista) {
		// TODO Auto-generated method stub

	}

	@Override
	public void GuardarLoginEstudiantes(LoginEstudiantes contactoEstudiantes) {


log.info("entradoALguardarLogin");


		if(contactoEstudiantes.getEstudiante_id().getId() ==null){

			if(loginEstudiantes_repository.findByCorreo(contactoEstudiantes.getCorreo()) != null)throw new  Response(" El Correo ya existe" );

			loginEstudiantes_repository.save(contactoEstudiantes);
		}else{
			log.info("Antes de la validacion correo");
			if(loginEstudiantes_repository.findByCorreo(contactoEstudiantes.getCorreo()) != null
					&& !(loginEstudiantes_repository.findById(contactoEstudiantes.getId()).get().getCorreo().equals(contactoEstudiantes.getCorreo()) ))   throw new  Response(" El Correo ya existe" );
log.info("despues de la validacion correo");

		loginEstudiantes_repository.save(contactoEstudiantes);
		}


	}


}
