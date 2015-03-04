package com.qt.functionsPage.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.quartz.service.QuartzJobService;
import com.qt.util.HttpRequest;
import com.qt.util.WriterLog;
import com.qt.util.WxJsSdk;

@Controller
public class GpsController {
	
	@Autowired
	private QuartzJobService quartzJobService;
	private Map<String, String> result;
	private String latitude;
	private String longitude;
	
	private Map<String, String> address;

	public Map<String, String> getResult() {
		return result;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Map<String, String> getAddress() {
		return address;
	}
	
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String execute(){
		result = new WxJsSdk().wxJsSdk(quartzJobService,"http://qtwxbp.duapp.com/myQt_index/gps?openId="+openId);
		return "success";
	}
	
	public String searchLoc(){
		tencentApi();
		return "success";
	}
	
	/**
	 * 百度地图逆地址解析API
	 */
//	private void baiduApi(){
//		address = new HashMap<String, String>();
//		String url = "http://api.map.baidu.com/geoconv/v1/";
//		String param = "coords="+latitude+","+longitude+"&ak=25227afb68e7c8a60138de19c5c4b2de&from=3";
//		try {
//			JSONObject json = new JSONObject(HttpRequest.sendGet(url, param)).getJSONArray("result").getJSONObject(0);
//			latitude = json.get("x").toString();
//			longitude = json.get("y").toString();
//			url = "http://api.map.baidu.com/geocoder/v2/";
//			param = "ak=25227afb68e7c8a60138de19c5c4b2de&location="+latitude+","+longitude+"&output=json&pois=1";
//			json = new JSONObject(HttpRequest.sendGet(url, param)).getJSONObject("result");
//			JSONObject addrJson = json.getJSONObject("addressComponent");
//			address.put("address", addrJson.get("province").toString() + addrJson.get("city").toString() 
//					+ addrJson.get("district").toString() + addrJson.get("street").toString());
//			address.put("pois", json.get("pois").toString());
//		} catch (JSONException e) {
//			WriterLog.writerLog("gps json error");
//		}
//	}
	
	/**
	 * 百度查询周边自定义地点
	 */
	public void baiduNearby(){
		address = new HashMap<String, String>();
		String url = "http://api.map.baidu.com/geosearch/v3/nearby";
		String param = "ak=25227afb68e7c8a60138de19c5c4b2de&geotable_id=94133&location="+longitude+","+latitude+"&radius=1000";
		JSONObject json;
		try {
			json = new JSONObject(HttpRequest.sendGet(url, param));
			address.put("pois", json.get("contents").toString());
		} catch (JSONException e) {
			WriterLog.writerLog("gps json error");
		}
	}
	
	/**
	 * 腾讯地图逆地址解析API
	 */
	private void tencentApi(){
//		latitude = "39.983424";
//		longitude = "116.322987";
		address = new HashMap<String, String>();
		String url = "http://apis.map.qq.com/ws/geocoder/v1/";
		String param = "location="+latitude+","+longitude+"&key=IAJBZ-PB4HV-5RZPL-UKVJP-ITD2Q-MPB5M&get_poi=1";
		try {
			JSONObject json = new JSONObject(HttpRequest.sendGet(url, param)).getJSONObject("result");
			JSONObject addrJson = json.getJSONObject("address_component");
			address.put("address", addrJson.get("province").toString() + addrJson.get("city").toString() 
					+ addrJson.get("district").toString() + addrJson.get("street").toString());
			address.put("pois", json.get("pois").toString());
		} catch (JSONException e) {
			WriterLog.writerLog("gps json error");
		}
	}
	
	/**
	 * 腾讯搜索周边接口
	 */
	public void tencentNearby(){
		String url = "http://apis.map.qq.com/ws/place/v1/search";
		//nearby(lat,lon,半径)，keyword=关键字(需要encodeURI编码),page_size=分页数,page_index=页码,orderby=排序
		String param = "boundary=nearby("+latitude+","+longitude+",1000)&keyword=&page_size=20&page_index=1&orderby=_distance&key=IAJBZ-PB4HV-5RZPL-UKVJP-ITD2Q-MPB5M";
		String json = HttpRequest.sendGet(url, param);
		System.out.println(json);
	}
	
	public static void main(String[] args) {
		GpsController gps = new GpsController();
		gps.searchLoc();
	}
}
