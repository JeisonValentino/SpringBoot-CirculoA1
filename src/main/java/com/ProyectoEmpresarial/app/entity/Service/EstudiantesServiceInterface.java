package com.ProyectoEmpresarial.app.entity.Service;

import java.util.List;

import com.ProyectoEmpresarial.app.dtoRest.Estudiantes.LoginEstudiantesDTO;
import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.ProyectoEmpresarial.app.dtoRest.Response;
import com.ProyectoEmpresarial.app.entity.Estudiantes.ContactoEstudiantes;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Pension;

public interface EstudiantesServiceInterface {


	public List<Estudiante> listaDeEstudiantes() throws Response;

	public Estudiante GuardarEstudiante(Estudiante estudiante)throws Exception;

	public void ModificarEstudiante(Estudiante estudiante);

	public List<ContactoEstudiantes>listaContactoporEstudiante(String id);

	public void GuardarListaContactoporEstudiante(List<ContactoEstudiantes> listaContactoEstudiante);

	public List<Pension> listadePensionesPorEstudiantes();

	public void GuardarListadePensionesPorEstudiantes(List<Pension> pensionesLista);

	public void GuardarLoginEstudiantes( LoginEstudiantes contactoEstudiantes);

}
