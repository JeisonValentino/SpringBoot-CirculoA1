package com.ProyectoEmpresarial.app.entity.Service;

import java.util.ArrayList;
import java.util.List;

import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.Repository.Empleado_Interface;
import com.ProyectoEmpresarial.app.Repository.File_Interface;
import com.ProyectoEmpresarial.app.Repository.Sede_Interface;
import com.ProyectoEmpresarial.app.Repository.TipoDocumentoIdentidad_Interface;
import com.ProyectoEmpresarial.app.Repository.User_Interface;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoDetailRequesModel;
import com.ProyectoEmpresarial.app.dtoRest.EmpleadoFiltrado;
import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.dtoRest.SedeRest;
import com.ProyectoEmpresarial.app.entity.Empleado;
import com.ProyectoEmpresarial.app.entity.File;
import com.ProyectoEmpresarial.app.entity.Sede;
import com.ProyectoEmpresarial.app.entity.TipoDocumentoIdentidad;
import com.ProyectoEmpresarial.app.entity.Usuario;

@Service
public class UsersEmpleadoServiceImplemet implements UsersEmpleadoServiceInterface{

	@Autowired
	Sede_Interface sedeInterface;
	@Autowired
TipoDocumentoIdentidad_Interface tipoDocumentoIdentidadInterface;

	@Autowired
	Empleado_Interface empleadoInterfaces;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	User_Interface userInterface;
	@Autowired
	File_Interface fileInterface;
	private Logger logger=LoggerFactory.getLogger(UsersEmpleadoServiceImplemet.class);
	@Override
	public Empleado crearEmpleado(EmpleadoDetailRequesModel empleadoDetailRequestModel,MultipartFile file){


	Empleado empleadoEntity=new Empleado();


		if(empleadoInterfaces.findByNumeroDocumento(empleadoDetailRequestModel.getNumeroDocumento())!=null) throw new  Response("Ocurrio un error al Guardar el empleado, El dni ya existe" );

		logger.info("paso el crear1");
		BeanUtils.copyProperties(empleadoDetailRequestModel, empleadoEntity);

		logger.info("paso el crear");
		try {
	TipoDocumentoIdentidad tipoDocumentoIdentidadEntity=	tipoDocumentoIdentidadInterface.findByNombreDocumento(empleadoDetailRequestModel.getIdTipoDocumentoIdentidad()) ;

			Sede sedeRest= modelMapper.map(empleadoDetailRequestModel.getIdSede() , Sede.class);
		empleadoEntity.setIdTipoDocumentoIdentidad(tipoDocumentoIdentidadEntity);
		empleadoEntity.setIdSede(sedeRest);
		File fileEntity =new File();

		if(file != null) {
			fileEntity.setData(file.getBytes());
			fileEntity.setName(file.getName());
			fileEntity.setType(file.getContentType());
			empleadoEntity.setId_files(fileInterface.save(fileEntity));
			}else {
				empleadoEntity.setId_files(fileInterface.save(fileEntity));
			}





		}catch (Exception e) {
			throw new Response("Ocurrio un error al Guardar el empleado, ponerse en contacto con el desarrollador del sistema" + e.getMessage());
		}
		empleadoInterfaces.save(empleadoEntity) ;
		return empleadoEntity  ;
	}

	@Override
	public void modificarEmpleado(EmpleadoDetailRequesModel empleadoDetailRequesModel,MultipartFile file)  {
		Empleado empleadoEntity=new Empleado();
		logger.info("antes del null 2");
		File fileEntity =new File();
		BeanUtils.copyProperties(empleadoDetailRequesModel,empleadoEntity);
		TipoDocumentoIdentidad tipoDocumentoIdentidadEntity=tipoDocumentoIdentidadInterface.findByNombreDocumento(empleadoDetailRequesModel.getIdTipoDocumentoIdentidad())	 ;
		Sede sedeRest= modelMapper.map(empleadoDetailRequesModel.getIdSede() , Sede.class);
			logger.info("antes del null 2");
			empleadoEntity.setIdTipoDocumentoIdentidad(tipoDocumentoIdentidadEntity);
			empleadoEntity.setIdSede(sedeRest);
			logger.info("antes del null 2");


			if((empleadoInterfaces.findByNumeroDocumento(empleadoDetailRequesModel.getNumeroDocumento())!=null) &&

					!((empleadoInterfaces.findById(empleadoDetailRequesModel.getId()).get().getNumeroDocumento()).equals(empleadoDetailRequesModel.getNumeroDocumento()))) throw new  Response(" El dni ya existe" );


			try {
				Empleado empleadoEntity2= empleadoInterfaces.findById(empleadoDetailRequesModel.getId()).get();

				if(file != null) {
					logger.info("pasando imagenes ");
					fileEntity.setData(file.getBytes());
					fileEntity.setName(file.getName());
					fileEntity.setType(file.getContentType());


					fileEntity.setId(empleadoEntity2.getId_files().getId());

					empleadoEntity.setId_files(fileInterface.save(fileEntity));

					}else {


			fileEntity.setData(empleadoEntity2.getId_files().getData());

			fileEntity.setName(empleadoEntity2.getId_files().getName());
			fileEntity.setType(empleadoEntity2.getId_files().getType());
			fileEntity.setId(empleadoEntity2.getId_files().getId());


			empleadoEntity.setId_files(fileInterface.save(fileEntity));

					}



			}catch (Exception e) {
				throw new Response("Ocurrio un error al Modificar el empleado, Ponerse en contacto con el administrador del sistema" + e.getMessage());
			}

			empleadoInterfaces.save(empleadoEntity);
		logger.info("se modifico el prestamo");

	}

	@Override
	public List<EmpleadoFiltrado> empleadoFiltrado() {
		List <Empleado>empleadoEntity =(List<Empleado>) empleadoInterfaces.findAll();
		List<Usuario>UsuariEntity =(List<Usuario>) userInterface.findAll();
		List<EmpleadoFiltrado>empleadoEntityFiltrado =new ArrayList<EmpleadoFiltrado>();

		try {
for(int i = 0 ; i< empleadoEntity.size();i++) {
	EmpleadoFiltrado empleado=new EmpleadoFiltrado();

		for(int a =0 ;a<UsuariEntity.size();a++) {
			Empleado empleadoNormal = new Empleado ();

				empleadoEntity.remove(UsuariEntity.get(a).getIdEmpleado());

		}


			empleado.setCode(empleadoEntity.get(i).getId());

			empleado.setName(empleadoEntity.get(i).getApellidoMaterno()+empleadoEntity.get(i).getApellidoPaterno()+empleadoEntity.get(i).getNombre());
			empleadoEntityFiltrado.add(empleado);
			}
} catch (Exception e) {

	logger.info("ocurrio un vacio"+e);
}


		return empleadoEntityFiltrado;
	}

	@Override
	public List<EmpleadoDetailRequesModel> ListaEmpleados() {
		List<Empleado>	empleadoEntity=	(List<Empleado>)empleadoInterfaces.findAll();
		ArrayList<EmpleadoDetailRequesModel> empleadoDetailRequestModel= new ArrayList<EmpleadoDetailRequesModel>();
		for(int i= 0 ; i<empleadoEntity.size();i++) {

			EmpleadoDetailRequesModel empleadoDetailEntidad = new EmpleadoDetailRequesModel();
			empleadoDetailEntidad.setId(empleadoEntity.get(i).getId());
			empleadoDetailEntidad.setNombre(empleadoEntity.get(i).getNombre());
			empleadoDetailEntidad.setApellidoMaterno(empleadoEntity.get(i).getApellidoMaterno());
			empleadoDetailEntidad.setApellidoPaterno(empleadoEntity.get(i).getApellidoPaterno());
			empleadoDetailEntidad.setNumeroDocumento(empleadoEntity.get(i).getNumeroDocumento());
			empleadoDetailEntidad.setTelefono(empleadoEntity.get(i).getTelefono());
			empleadoDetailEntidad.setIdTipoDocumentoIdentidad(tipoDocumentoIdentidadInterface.findById(empleadoEntity.get(i).getIdTipoDocumentoIdentidad().getId()).get().getNombreDocumento());
	Sede sedeEntity=sedeInterface.findById(empleadoEntity.get(i).getIdSede().getId()).get();

			SedeRest sedeRest= modelMapper.map(sedeEntity , SedeRest.class);

		empleadoDetailEntidad.setIdSede((sedeRest) );
		empleadoDetailEntidad.setDireccion(empleadoEntity.get(i).getDireccion());
		empleadoDetailEntidad.setEstadoCivil(empleadoEntity.get(i).getEstadoCivil());
		empleadoDetailEntidad.setGradoInstruccion(empleadoEntity.get(i).getGradoInstruccion());
		empleadoDetailEntidad.setConocimientoInformatico(empleadoEntity.get(i).getConocimientoInformatico()+"");
		empleadoDetailEntidad.setNombreTotal(empleadoEntity.get(i).getNombre()+" "+empleadoEntity.get(i).getApellidoMaterno()+" "+empleadoEntity.get(i).getApellidoPaterno());


		if(empleadoEntity.get(i).getId_files()!=null) {
			empleadoDetailEntidad.setPhoto(empleadoEntity.get(i).getId_files().getData());
			}






		empleadoDetailRequestModel.add(empleadoDetailEntidad);
		}
				return empleadoDetailRequestModel;
	}


	@Override
	public List<Sede>  ListarSedes() {
		List<Sede> listasSedesEntity =(List<Sede>) sedeInterface.findAll();




				return listasSedesEntity;
	}

	@Override
	public List<SedeRest> listaSedesReal() {
		List<Sede>listaSede=(List<Sede>) sedeInterface.findAll();
		ArrayList<SedeRest> listaAgregable = new ArrayList<SedeRest>();

		for(int i = 0 ; i<listaSede.size();i++) {
			SedeRest sedeEntity= new SedeRest();
			sedeEntity.setId(listaSede.get(i).getId());
		sedeEntity.setNombre(listaSede.get(i).getNombre());
			sedeEntity.setRuc(listaSede.get(i).getRuc());
			sedeEntity.setTelefono(listaSede.get(i).getTelefono());
			sedeEntity.setDireccion(listaSede.get(i).getDireccion());
			listaAgregable.add(sedeEntity);

		}

		return listaAgregable;
	}

	@Override
	public Sede CrearSedes(SedeRest sedeRest) {
Sede sedeEntity =new Sede();

		sedeEntity.setDireccion(sedeRest.getDireccion());
		sedeEntity.setNombre(sedeRest.getNombre());
		sedeEntity.setRuc(sedeRest.getRuc());
		sedeEntity.setTelefono(sedeRest.getTelefono());


		sedeInterface.save(sedeEntity);
		return sedeEntity;
	}

	@Override
	public Sede EditarSede(SedeRest sedeRest) {
		Sede sedeEntity =new Sede();
		sedeEntity.setId(sedeRest.getId());
		sedeEntity.setDireccion(sedeRest.getDireccion());
		sedeEntity.setNombre(sedeRest.getNombre());
		sedeEntity.setRuc(sedeRest.getRuc());
		sedeEntity.setTelefono(sedeRest.getTelefono());
		sedeInterface.save(sedeEntity);
		return sedeEntity;
	}


}
