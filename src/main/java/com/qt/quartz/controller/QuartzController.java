package com.qt.quartz.controller;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.stereotype.Controller;

import com.qt.quartz.service.QuartzService;
import com.qt.util.WriterLog;

import java.text.ParseException;

/**
 * Created by tiann on 2014/11/6.
 */
@Controller
public class QuartzController {
    @Autowired
    private QuartzService quartzService;

    private Scheduler schedulerFactory;
    public void setSchedulerFactory(Scheduler schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    public void taskJobLog(){
        quartzService.taskJob();
    }

    public void reScheduleJob(){
        try {
            CronTriggerBean trigger = (CronTriggerBean)schedulerFactory.getTrigger("doTime", Scheduler.DEFAULT_GROUP);
            String originConExpression = trigger.getCronExpression();
            String dbCronExpression = quartzService.taskTime();
            boolean willchange = !(dbCronExpression.equals("")||dbCronExpression==null||dbCronExpression.equals(originConExpression));
            if(willchange){
                try {
                    trigger.setCronExpression(dbCronExpression);
                    schedulerFactory.rescheduleJob("doTime", Scheduler.DEFAULT_GROUP, trigger);
                    WriterLog.writerLog("Update Time Job :"+dbCronExpression);//输出日志
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
