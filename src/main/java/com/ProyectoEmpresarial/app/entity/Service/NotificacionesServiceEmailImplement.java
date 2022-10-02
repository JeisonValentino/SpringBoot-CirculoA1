package com.ProyectoEmpresarial.app.entity.Service;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class NotificacionesServiceEmailImplement implements NotificacionesServiceEmailInterface{


    private TaskScheduler taskScheduler;



    public NotificacionesServiceEmailImplement(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(String jobId, Runnable tasklet, String cronExpression) {
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }


    @Override
    public void EnviarMensajeEmailUnico() {

    }

    @Override
    public void ImplementarNotificacionAutomatico() {

    }

    @Override
    public void ModificarNotificacionAutomatico() {

    }


}
