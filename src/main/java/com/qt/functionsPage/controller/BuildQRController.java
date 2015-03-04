package com.qt.functionsPage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.functionsPage.model.QrInfo;
import com.qt.functionsPage.service.QrInfoService;
import com.qt.quartz.service.QuartzJobService;
import com.qt.util.HttpRequest;
import com.qt.util.WxJsSdk;

@Controller
public class BuildQRController {
	
	@Autowired
    private QuartzJobService quartzJobService;
	@Autowired
	private QrInfoService qrInfoService;
	
	private String info;
	private String retURL;
	private Map<String, String> result;
	//自定义二维码接收
	private String qrurl;
	private String option;
	
	
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	public String getRetURL() {
		return retURL;
	}
	public void setQrurl(String qrurl) {
		this.qrurl = qrurl;
	}
	public Map<String, String> getResult() {
		return result;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	public String execute(){
		return "success";
	}
	
	public String buildQr(){
		result = new WxJsSdk().wxJsSdk(quartzJobService,"http://qtwxbp.duapp.com/myQt_index/buildQrPage?openId="+openId);
		String sceneId = Math.round(Math.random()*(899)+100) + new SimpleDateFormat("HHMMss").format(new Date().getTime());
		String accessToken = quartzJobService.selectTestAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		String json = "{\"expire_seconds\": 1800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+sceneId+"}}}";
		String ticket = "";
		try {
			JSONObject result = new JSONObject(HttpRequest.sendPost(url, json));
			ticket = result.getString("ticket");
		} catch (JSONException e) {
			System.out.println("获取二维码：json解析异常");
		}
		retURL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		QrInfo qrInfo = new QrInfo();
		qrInfo.setScene_id(sceneId);
		qrInfo.setTicket(ticket);
		qrInfo.setCreat_date(new Date());
		qrInfo.setInfo(info);
		qrInfo.setPassword("");
		qrInfo.setIsdel("0");
		qrInfoService.insertQrInfo(qrInfo);
		if(ticket==null||"".equals(ticket)||"myqr".equalsIgnoreCase(option)){
			retURL = "http://qr.liantu.com/api.php?text="+sceneId+"&w=300";//调用外部API产生二维码
		}
		return "success";
	}
	
	public String qrMsg(){
		if(qrurl!=null){
			info = qrInfoService.searchQrInformation(qrurl);
			if(info==null||"".equals(info)){
				info = qrurl;
			}
		}else{
			info = "好像什么都没留下哦...";
		}
		return "success";
	}
	
}
