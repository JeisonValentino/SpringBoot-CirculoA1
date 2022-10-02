package com.ProyectoEmpresarial.app.entity.Service;

import com.ProyectoEmpresarial.app.entity.Estudiantes.NotificacionEstudiantes;
import org.springframework.stereotype.Service;

@Service
public class TaskDefinitionBean implements Runnable{

    private NotificacionEstudiantes notificacionEstudiantes;
    @Override
    public void run() {

        System. out .println("Acción en ejecución: " + notificacionEstudiantes.getActionType());
        System. out .println("Con datos: " + notificacionEstudiantes.getData());

    }
public NotificacionEstudiantes getNotificacionEstudiantes(){
        return notificacionEstudiantes;
}

public void setNotificacionEstudiantes(NotificacionEstudiantes notificacionEstudiantes){
        this.notificacionEstudiantes=notificacionEstudiantes;
}

}
