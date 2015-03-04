package com.qt.mainPage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.quartz.service.QuartzJobService;
import com.qt.util.WxJsSdk;

@Controller
public class MainPage {
	@Autowired
	private QuartzJobService quartzJobService;
	private Map<String, String> result;
	
	public Map<String, String> getResult() {
		return result;
	}
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String execute(){
		result = new WxJsSdk().wxJsSdk(quartzJobService,"http://qtwxbp.duapp.com/myQt_index/main?openId="+openId);
		return "success";
	}
	
}
