package com.ProyectoEmpresarial.app.entity.Estudiantes;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tipoNotificacion")
@Data
public class TipoNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    private String concepto;

    private String Descripcion;


    @OneToMany(mappedBy ="tipoNotificacion_id" , cascade = CascadeType.ALL)
    private List<NotificacionEstudiantes> notificacionEstudiantesList ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

}
