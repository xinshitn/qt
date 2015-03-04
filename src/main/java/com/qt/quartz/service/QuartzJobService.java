package com.qt.quartz.service;

import java.util.Map;

/**
 * 定时任务业务接口
 */
public interface QuartzJobService {
    /**
     * 获取access_token
     * @return
     */
    String catchAccessToken();
    
    /**
     * 缓存access_token
     */
    void saveAccessToken(String accessToken);
    
    /**
     * 查询access_token
     * @return
     */
    String selectAccessToken();
    
    /**
     * 获取jsapi_ticket票据
     * @param accessToken
     * @return
     */
    String catchJsapiTicket(String accessToken); 

    /**
     * 缓存jsapi_ticket票据
     * @param accessToken
     */
    void saveJsapiTicket(String jsapiTicket);
    
    /**
     * 查询jsapi_ticket
     * @return
     */
    String selectJsapiTicket();
    
    /**
     * 获取JS-SDK权限验证的签名
     * @return
     */
    Map<String, String> catchSignature(String jsapi_ticket, String url);
    
    /**
     * 查询wx_ticket表
     * @param ticketKey
     * @return
     */
    String selectWxTicket(String ticketKey);
    
    /**
     * 获取测试号AccessToken
     * @return
     */
    String catchTestAccessToken();
    
    /**
     * 缓存test_access_token
     */
    void saveTestAccessToken(String accessToken);
    
    /**
     * 查询test_access_token
     * @return
     */
    String selectTestAccessToken();

}
