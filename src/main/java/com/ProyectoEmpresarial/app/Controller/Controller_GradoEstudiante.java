package com.ProyectoEmpresarial.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.GradoEstudiante_Repository;
import com.ProyectoEmpresarial.app.entity.Estudiantes.GradoEstudiante;

@RestController
@RequestMapping("/grado")
public class Controller_GradoEstudiante {


	@Autowired
	GradoEstudiante_Repository gradoEstudiante_Repository;
	@GetMapping("/listaGrado")
	public List<GradoEstudiante> ListarGradoEstudiante(){

		return (List<GradoEstudiante>)gradoEstudiante_Repository.findAll();
	}
}
