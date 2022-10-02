package com.ProyectoEmpresarial.app.entity.Estudiantes;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notificacioneEstudiante")
@Data
public class NotificacionEstudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    private String cronExpression;
    private String actionType;
    private String data;


            @ManyToOne(optional = false,fetch = FetchType.LAZY)
            @JoinColumn(name = "TipoNotificacion_id", referencedColumnName = "id")
             private  TipoNotificacion tipoNotificacion_id;

            private String Plataforma;

            @ManyToOne(optional = false,fetch = FetchType.LAZY)
            @JoinColumn(name = "Estudiante_id", referencedColumnName = "id")
            private Estudiante estudiante_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

}
