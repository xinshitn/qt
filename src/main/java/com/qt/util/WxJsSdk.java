package com.qt.util;

import java.util.HashMap;
import java.util.Map;

import com.qt.quartz.service.QuartzJobService;

public class WxJsSdk {
	
	public Map<String,String> wxJsSdk(QuartzJobService quartzJobService,String url){
		Map<String,String> result = new HashMap<String, String>();
		String ticket = quartzJobService.selectJsapiTicket();
		Map<String, String> map = quartzJobService.catchSignature(ticket, url);
		result.put("appId", quartzJobService.selectWxTicket("appID"));
		result.put("timestamp", map.get("timestamp"));
		result.put("nonceStr", map.get("nonceStr"));
		result.put("signature", map.get("signature"));
		return result;
	}
}
