package com.qt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qt.quartz.service.QuartzJobService;
import com.qt.util.WxJsSdk;

public class TestAcion {
	
	@Autowired
	private QuartzJobService quartzJobService;
	private Map<String, String> result;
	private String openId;
	public Map<String, String> getResult() {
		return result;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String execute(){
		result = new WxJsSdk().wxJsSdk(quartzJobService,"http://qtwxbp.duapp.com/myQt_index/?openId="+openId);
        return "success";
    }
	
	public String decorating(){
		result = new WxJsSdk().wxJsSdk(quartzJobService,"http://qtwxbp.duapp.com/myQt_index/decorating");
        return "success";
	}
}
