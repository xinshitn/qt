package com.qt.quartz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.quartz.service.QuartzJobService;

/**
 * Created by VicenTN on 14-12-30.
 */
@Controller
public class QuartzJobController {
    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 获取access_token
     * @return
     */
    public String catchAccessToken(){
        String accessToken = quartzJobService.catchAccessToken();
        return accessToken;
    }
    
    /**
     * 缓存access_token
     * @param accessToken
     */
    public void saveAccessToken(String accessToken){
    	quartzJobService.saveAccessToken(accessToken);
    }
    
    /**
     * 查询access_token
     * @return
     */
    public String selectAccessToken(){
    	return quartzJobService.selectAccessToken();
    }
    
    /**
     * 获取jsapi_ticket
     * @param accessToken
     * @return
     */
    public String catchJsapiTicket(String accessToken){
    	String jsapiTicket = quartzJobService.catchJsapiTicket(accessToken);
    	return jsapiTicket;
    }
    
    /**
     * 缓存jsapi_ticket
     * @param jsapiTicket
     */
    public void saveJsapiTicket(String jsapiTicket){
    	quartzJobService.saveJsapiTicket(jsapiTicket);
    }
    
    /**
     * 查询jsapi_ticket
     * @return
     */
    public String selectJsapiTicket(){
    	return quartzJobService.selectJsapiTicket();
    }
    
    /**
     * 生成JS调用签名
     * @param jsapi_ticket
     * @param url
     * @return signature相关信息集合（包括noncestr,timestamp,url）
     */
    public Map<String, String> catchSignature(String noncestr, String jsapi_ticket, String timestamp, String url){
    	Map<String, String> signature = quartzJobService.catchSignature(jsapi_ticket, url);
    	return signature;
    }
    
    /**
     * 获取测试号AccessToken
     * @return
     */
    public String catchTestAccessToken(){
    	return quartzJobService.catchTestAccessToken();
    }
    
    /**
     * 缓存test_access_token
     */
    public void saveTestAccessToken(String accessToken){
    	quartzJobService.saveTestAccessToken(accessToken);
    }

}
