package com.qt.quartz.service.impl;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.quartz.dao.QuartzJobMapper;
import com.qt.quartz.service.QuartzJobService;
import com.qt.util.HttpRequest;
import com.qt.util.Sign;
import com.qt.util.WriterLog;

/**
 * Created by VicenTN on 14-12-30.
 */
@Service
@Transactional
public class QuartzJobServiceImp implements QuartzJobService {

	@Autowired
	private QuartzJobMapper quartzJobMapper;
	
	@Override
	public String catchAccessToken() {
		//TODO 输出日志
		WriterLog.writerLog("Catch AccessToken");
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		String appid = quartzJobMapper.selectWxTicket("appID");
		String secret = quartzJobMapper.selectWxTicket("appsecret");
		String param = "grant_type=client_credential&appid="+appid+"&secret="+secret;
		String json = HttpRequest.sendGet(url, param);
		String accessToken = "";
		try {
			JSONObject jsonObj = new JSONObject(json);;
			accessToken = jsonObj.get("access_token").toString();
		} catch (JSONException e) {
			WriterLog.writerLog("catchAccessToken: Json error!");
		}
		return accessToken;
	}

	@Override
	public String catchJsapiTicket(String accessToken) {
		// TODO 输出日志
		WriterLog.writerLog("Catch JsapiTicket");
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		String param = "access_token="+accessToken+"&type=jsapi";
		String json = HttpRequest.sendGet(url, param);
		String jsapiTicket = "";
		try {
			JSONObject jsonObj = new JSONObject(json);
			String errcode = jsonObj.get("errcode").toString();
			if("0".equals(errcode)){
				jsapiTicket = jsonObj.get("ticket").toString();
			}else{
				WriterLog.writerLog("catchJsapiTicket: "+jsonObj.get("errmsg").toString());
				return "error";
			}
		} catch (JSONException e) {
			WriterLog.writerLog("catchJsapiTicket: Json error!");
		}
		return jsapiTicket;
	}

	@Override
	public Map<String, String> catchSignature(String jsapi_ticket, String url) {
		Map<String, String> map = Sign.sign(jsapi_ticket, url);
		return map;
	}

	@Override
	public void saveAccessToken(String accessToken) {
		// TODO 输出日志
		WriterLog.writerLog("Save AccessToken");
		quartzJobMapper.updateAccessToken(accessToken);
	}

	@Override
	public void saveJsapiTicket(String jsapiTicket) {
		// TODO 输出日志
		WriterLog.writerLog("Save JsapiTicket");
		quartzJobMapper.updateJsapiTicket(jsapiTicket);
		
	}

	@Override
	public String selectAccessToken() {
		return quartzJobMapper.catchAccessToken();
	}

	@Override
	public String selectJsapiTicket() {
		return quartzJobMapper.catchJsapiTicket();
	}

	@Override
	public String selectWxTicket(String ticketKey) {
		return quartzJobMapper.selectWxTicket(ticketKey);
	}

	@Override
	public String catchTestAccessToken() {
		//TODO 输出日志
		WriterLog.writerLog("Catch TestAccessToken");
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		String appid = quartzJobMapper.selectWxTicket("testAppID");
		String secret = quartzJobMapper.selectWxTicket("testAppsecret");
		String param = "grant_type=client_credential&appid="+appid+"&secret="+secret;
		String json = HttpRequest.sendGet(url, param);
		String accessToken = "";
		try {
			JSONObject jsonObj = new JSONObject(json);;
			accessToken = jsonObj.get("access_token").toString();
		} catch (JSONException e) {
			WriterLog.writerLog("catchTestAccessToken: Json error!");
		}
		return accessToken;
	}

	@Override
	public void saveTestAccessToken(String accessToken) {
		// TODO 输出日志
		WriterLog.writerLog("Save TestAccessToken");
		quartzJobMapper.updateTestAccessToken(accessToken);
	}

	@Override
	public String selectTestAccessToken() {
		return quartzJobMapper.catchTestAccessToken();
	}
}
