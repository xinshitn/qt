package com.qt.quartz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 定时任务所需查询
 */
@Repository
public interface QuartzJobMapper {
    /**
     * 更新Access_Token
     * @return
     */
    void updateAccessToken(@Param("accessToken")String accessToken);
    
    /**
     * 更新jsapi_ticket
     * @param value
     * @return
     */
    void updateJsapiTicket(@Param("jsapiTicket")String jsapiTicket);
    
    /**
     * 查询Access_Token
     * @return
     */
    String catchAccessToken();
    
    /**
     * 查询Access_Token
     * @return
     */
    String catchJsapiTicket();
    
    /**
     * 查询wx_ticket表
     * @param ticketKey
     * @return
     */
    String selectWxTicket(@Param("ticketKey")String ticketKey);
    
    /**
     * 更新Access_Token
     * @return
     */
    void updateTestAccessToken(@Param("accessToken")String accessToken);
    
    /**
     * 查询Test_Access_Token
     * @return
     */
    String catchTestAccessToken();
}
