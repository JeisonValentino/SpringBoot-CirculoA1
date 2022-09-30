package com.ProyectoEmpresarial.app.Repository.Estudiantes_Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ProyectoEmpresarial.app.entity.Estudiantes.LoginEstudiantes;

@Repository
public interface LoginEstudiantes_Repository extends CrudRepository<LoginEstudiantes,Integer> {

    @Query("SELECT C FROM LoginEstudiantes C JOIN Estudiante E ON C.estudiante_id=E.id WHERE E.id = :id")
    public LoginEstudiantes obtenerUsuarioPorEstudiante(@Param("id") String id);

    public LoginEstudiantes findByCorreo(String correo);
}
