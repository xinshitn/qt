package com.qt.gamePage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.gamePage.dao.G2048Mapper;
import com.qt.gamePage.service.G2048Service;

@Service
@Transactional
public class G2048ServiceImpl implements G2048Service {
	
	@Autowired
	G2048Mapper g2048Mapper;

	@Override
	public int findTop(String openId) {
		return g2048Mapper.findTop(openId);
	}

	@Override
	public void editTop(Map<String, Object> map) {
		g2048Mapper.editTop(map);
	}

	@Override
	public void saveBoard(Map<String, Object> map) {
		g2048Mapper.saveBoard(map);
	}

	@Override
	public Map<String, Object> readBoard(String openId) {
		return g2048Mapper.readBoard(openId);
	}

	@Override
	public void deleteBoard(String openId) {
		g2048Mapper.deleteBoard(openId);
	}

	@Override
	public List<String> chartsList() {
		List<Map<String, String>> result = g2048Mapper.chartsList();
		List<String> list = new ArrayList<String>();
		for(Map<String, String> map : result){
			list.add(map.get("itemName")+","+String.valueOf(map.get("itemValue")));
		}
		return list;
	}

	@Override
	public String findName(String openId) {
		return g2048Mapper.findName(openId);
	}

}
