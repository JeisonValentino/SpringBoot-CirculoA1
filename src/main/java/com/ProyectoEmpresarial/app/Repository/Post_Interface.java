package com.ProyectoEmpresarial.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ProyectoEmpresarial.app.entity.Mesa_de_Partes.Post;


@Repository
public interface Post_Interface extends CrudRepository<Post, Long> {


	@Query("SELECT P FROM Post P JOIN Solicitud S ON S.id=P.idSolicitud.id WHERE P.idSolicitud.id LIKE %?1%")
	public List<Post> post(String id);


}
