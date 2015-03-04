package com.qt.gamePage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.gamePage.dao.SswdMapper;
import com.qt.gamePage.service.SswdService;

@Service
@Transactional
public class SswdServiceImpl implements SswdService {
	
	@Autowired
	private SswdMapper sswdMapper;

	@Override
	public String findName(String openId) {
		String name = sswdMapper.findName(openId);
		return name;
	}

	@Override
	public String addName(Map<String, String> map) {
		String name = sswdMapper.findName(map.get("openId"));
		if(name == null||"".equals(name)){
			sswdMapper.addName(map);
		}else if(!name.equals(map.get("name"))){
			sswdMapper.editName(map);
		}
		return "suc";
	}

	@Override
	public String buildRoom(String password, String openId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("openId", openId);
		int count = 1, roomNum = 1000;
		while(count>0){
			roomNum++;
			count = Integer.valueOf(sswdMapper.countRoomPeople(String.valueOf(roomNum)));
		}
		map.put("roomNum", roomNum+"");
		String userName = sswdMapper.roomOwner(roomNum+"");
		if(userName==null||"".equalsIgnoreCase(userName)){
			sswdMapper.buildRoom(map);
		}else{
			sswdMapper.editRoomOwner(map);
		}
		sswdMapper.editUserRoom(map);
		return "suc";
	}

	@Override
	public String findRoomNum(String openId) {
		return sswdMapper.findRoomNum(openId);
	}

	@Override
	public List<String> findUsers(String roomNum) {
		List<Map<String, String>> result = sswdMapper.findUsers(roomNum);
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result){
			list.add(map.get("itemName")+","+map.get("itemValue"));
		}
		return list;
	}

	@Override
	public int countRoomNUm(String roomNum) {
		int count = Integer.valueOf(sswdMapper.countRoomPeople(roomNum));
		return count;
	}

	@Override
	public String findRoomPassword(String roomNum) {
		return sswdMapper.findRoomPassword(roomNum);
	}

	@Override
	public List<String> findUsersOpenId(String roomNum) {
		List<Map<String, String>> result = sswdMapper.findUsers(roomNum);
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result){
			list.add(map.get("itemName"));
		}
		return list;
	}

	@Override
	public void addCard(String index, String card, String openId, String cardNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("index", index);
		map.put("card", card);
		map.put("openId", openId);
		map.put("cardNum", cardNum);
		sswdMapper.addCard(map);
	}

	@Override
	public List<Map<String, String>> catchCard(String index) {
		List<Map<String, String>> result = sswdMapper.catchCard(index);
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result){
			list.add(map.get("itemName"));
		}
		return sswdMapper.catchCard(index);
	}

	@Override
	public String myCard(String openId) {
		return sswdMapper.myCard(openId);
	}
	
	@Override
	public String myCardIndex(String openId) {
		return sswdMapper.myCardIndex(openId);
	}

	@Override
	public void enterRoom(Map<String,String> map) {
		sswdMapper.editUserRoom(map);
	}

	@Override
	public String roomOwner(String roomNum) {
		return sswdMapper.roomOwnerId(roomNum);
	}

	@Override
	public String checkCardNum(String openId) {
		return sswdMapper.checkCardNum(openId);
	}

	@Override
	public void outRoomAll(String roomNum) {
		sswdMapper.outRoomAll(roomNum);
		sswdMapper.deleteRoom(roomNum);
	}

	@Override
	public void outRoomOne(String openId) {
		sswdMapper.outRoomOne(openId);
	}

}
