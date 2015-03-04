package com.qt.quartz.dao;

import org.springframework.stereotype.Repository;

/**
 * 定时任务自动接口
 */
@Repository
public interface QuartzMapper {

    /**
     * 取得数据库定时时间
     * @return
     */
    String taskTime();
}