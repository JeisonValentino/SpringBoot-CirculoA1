package com.ProyectoEmpresarial.app.entity.Service;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AutomatizacionConfigurer {

    @Scheduled(fixedDelay  =1000*60)
public void EnviarMensajeCorreoAutomatico(){
    System.out.println("inicio automatizacion");
}
}
