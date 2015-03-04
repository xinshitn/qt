package com.qt.quartz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.quartz.controller.QuartzJobController;
import com.qt.quartz.dao.QuartzMapper;
import com.qt.quartz.service.QuartzService;
import com.qt.util.WriterLog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tiann on 2014/11/6.
 */
@Service
@Transactional
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private QuartzMapper quartzmapper;
    @Autowired
    private QuartzJobController quartzJobController;

    @Override
    public void taskJob(){
        String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        System.out.println("定时任务时间戳: "+nowTime);
//        WriterLog.writerLog("timeJob stamp: "+nowTime);//TODO 输出日志
        updateAcJs();//更新access_token和jsapiTicket

    }

    @Override
    public String taskTime(){
        return quartzmapper.taskTime();
    }
    
    /**
     * 获取access_token和jsapiTicket
     */
    private void updateAcJs(){
    	WriterLog.writerLog("====================Auto Update Info: accessToken BEGIN====================");//TODO 输出日志
        //更新access_token
        String accessToken = quartzJobController.catchAccessToken();
        quartzJobController.saveAccessToken(accessToken);
        String jsapiTicket = quartzJobController.catchJsapiTicket(accessToken);
        quartzJobController.saveJsapiTicket(jsapiTicket);
        //更新test_access_token
        String test_access_token = quartzJobController.catchTestAccessToken();
        quartzJobController.saveTestAccessToken(test_access_token);
        WriterLog.writerLog("====================Auto Update Info: accessToken END====================");//TODO 输出日志
    }
}
