package com.ProyectoEmpresarial.app.Controller;

import com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository.EstadoEstudiante_Repository;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estado_Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    EstadoEstudiante_Repository estudiante_repository;

    @GetMapping("/obtenerListaEstado")
    public List<Estado_Estudiante> obtenerListaEstado(){


        return  (List<Estado_Estudiante>)estudiante_repository.findAll();
    }





}
