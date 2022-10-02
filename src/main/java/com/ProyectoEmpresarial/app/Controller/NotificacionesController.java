package com.ProyectoEmpresarial.app.Controller;


import com.ProyectoEmpresarial.app.dtoRest.Estudiantes.NotificacionEstudiantesDto;
import com.ProyectoEmpresarial.app.entity.Estudiantes.NotificacionEstudiantes;
import com.ProyectoEmpresarial.app.entity.Service.NotificacionesServiceEmailImplement;
import com.ProyectoEmpresarial.app.entity.Service.TaskDefinitionBean;
import com.ProyectoEmpresarial.app.security.CredencialesCorreoConstantes;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionesController  {


private JavaMailSender sender;

private NotificacionesServiceEmailImplement notificacionesServiceEmailImplement;
private TaskDefinitionBean taskDefinitionBean;

        private ModelMapper modelMapper;

        public NotificacionesController(ModelMapper modelMapper,JavaMailSender sender , NotificacionesServiceEmailImplement notificacionesServiceEmailImplement , TaskDefinitionBean taskDefinitionBean) {
                this.sender = sender;
                this.notificacionesServiceEmailImplement=notificacionesServiceEmailImplement;
        this.taskDefinitionBean=taskDefinitionBean;
        this.modelMapper=modelMapper;
        }


        @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
        public void scheduleATask(@RequestBody NotificacionEstudiantesDto taskDefinition) {

                NotificacionEstudiantes notificacionEstudiantes = modelMapper.map(taskDefinition,NotificacionEstudiantes.class );
                taskDefinitionBean.setNotificacionEstudiantes(notificacionEstudiantes);

                notificacionesServiceEmailImplement.scheduleATask(UUID.randomUUID().toString(), taskDefinitionBean, taskDefinition.getCronExpression());
        }



        @PostMapping
public String enviarMensaje(String string) {
        String bodyMessage = "Este es un ejemplo de correo ID=" + UUID.randomUUID().toString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("jeisonvalentino11@gmail.com");
        message.setFrom(CredencialesCorreoConstantes.CredencialesCorreo.CORREO_NOTIFICACIONES);
        message.setSubject("Mensaje de Correo Electronico");
        message.setText(bodyMessage);

        sender.send(message);

        return string;
}

}
