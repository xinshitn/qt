package com.qt.gamePage.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.gamePage.service.SswdService;

@Controller
public class SswdController {
	
	@Autowired
	private SswdService sswdService;
	
	private String openId;
	private String name;
	private String result;
	private String password;
	private String roomNum;
	private List<String> users;
	private String roomOwner;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getResult() {
		return result;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public List<String> getUsers() {
		return users;
	}

	public String getRoomOwner() {
		return roomOwner;
	}

	/**
	 * 默认调用方法
	 * @return
	 */
	public String execute(){
		if(openId==null||"".equals(openId)){
			return "noOpenId";
		}
		name = sswdService.findName(openId);
		gamePage();
		if(roomNum!=null&&!"".equals(roomNum)&&!"0".equals(roomNum)){
			return "hadroom";
		}
		return "success";
	}

	//进入游戏
	public String enterGame(){
		Map<String, String> map = new HashMap<String, String>();
		if(name==null||"".equals(name.trim())){
			result = "noName";
			return "success";
		}
		map.put("name", name.trim());
		map.put("openId", openId);
		result = sswdService.addName(map);
		return "success";
	}
	
	//创建房间
	public String sswdBuildRoom(){
		if(password==null)password = "";
		result = sswdService.buildRoom(password, openId);
		return "success";
	}
	
	//房间人员列表页
	public String sswdUsersListPage(){
		roomNum = sswdService.findRoomNum(openId);
		result = sswdService.countRoomNUm(roomNum)+"";//统计房间人员数
		roomOwner = sswdService.roomOwner(roomNum);
		if(openId==null||!openId.equals(roomOwner)){
			return "noLogin";
		}
		return "success";
	}
	
	//获取房间人数
	public String roomPeopleNum(){
		result = sswdService.countRoomNUm(roomNum)+"";//统计房间人员数
		return "success";
	}
	
	//获取房间人员列表
	public String sswdUsersList(){
		users = sswdService.findUsers(roomNum);
		return "success";
	}
	
	//进入房间
	public String enterRoom(){
		result = "suc";
		int count = sswdService.countRoomNUm(roomNum);
		if(count>0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("roomNum", roomNum);
			map.put("openId", openId);
			String pw = sswdService.findRoomPassword(roomNum);
			if(pw==null||"".equals(pw)){
				sswdService.enterRoom(map);
				result = "suc";
			}else{
				if(pw.equals(password)){
					result = "suc";
				}else{
					result = "fal";
				}
			}
		}else{
			result = "fal";
		}
		return "success";
	}
	
	//开始游戏
	public String playGame(){
		int count = 1+(int)(Math.random()*sswdService.countRoomNUm(roomNum));//获得房间人数后，随机生产一个卧底序号
		String index = 1+(int)(Math.random()*161)+"";//生成词组随机序号用于取词
		Map<String, String> cards = sswdService.catchCard(index).get(0);
		String undercover = cards.get("itemName");
		String civilian = cards.get("itemValue");
		List<String> usersOpenIds = sswdService.findUsersOpenId(roomNum);
		String[] arrOpenIds = getList(usersOpenIds);
		int i = 1;
		for(String openId : arrOpenIds){
			if(count==i){
				sswdService.addCard(i+"", undercover, openId, index);
			}else{
				sswdService.addCard(i+"", civilian, openId, index);
			}
			i++;
		}
		result = "suc";
		return "success";
	}
	
	//游戏区页面
	public String gamePage(){
		roomNum = sswdService.findRoomNum(openId);
		roomOwner = sswdService.roomOwner(roomNum);
		return "success";
	}
	
	//查询当前抽中的卡片
	public String myCard(){
		result = sswdService.myCard(openId);
		return "success";
	}
	
	//查询当前抽中卡片游戏序号
	public String myCardIndex(){
		result = sswdService.myCardIndex(openId);
		return "success";
	}
	
	//检查卡片序号
	public String checkCardNum(){
		result = sswdService.checkCardNum(openId);
		return "success";
	}
	
	//退出房间
	public String outRoom(){
		if(roomNum==null||"".equals(roomNum)){
			sswdService.outRoomOne(openId);
		}else{
			sswdService.outRoomAll(roomNum);
		}
		result = "suc";
		return "success";
	}
	
	//显示玩家列表
	public String sswdShowUsersListPage(){
		roomNum = sswdService.findRoomNum(openId);
		result = sswdService.countRoomNUm(roomNum)+"";//统计房间人员数
		roomOwner = sswdService.roomOwner(roomNum);
		return "success";
	}
	
	//踢出玩家
	public String removeOut(){
		sswdService.outRoomOne(openId);
		result = "suc";
		return "success";
	}
	
	/**
	 * 随机排序
	 * @param list 要进行随机排序的数据集合
	 * @return 排序结果
	 */
	public String[] getList(List<String> list){
		//数组长度
		int count = list.size();
		//结果集
		String[] resultList = new String[count];
		//用一个LinkedList作为中介
		LinkedList<String> temp = new LinkedList<String>();
		//初始化temp
		for(int i = 0; i<count; i++){
			temp.add(list.get(i));
		}
		//取数
		Random rand = new Random();
		for(int i = 0;i<count; i++){
			int num = rand.nextInt(count - i);
			resultList[i] = (String) temp.get(num);
			temp.remove(num);
		}
		return resultList;
	}
}
