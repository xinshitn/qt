package com.qt.gamePage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.gamePage.service.G2048Service;

@Controller
public class G2048Controller {
	@Autowired
	G2048Service g2048Service;
	private int topscore;
	private int score;
	private String openId;
	private String board;
	private List<String> users;
	private String name;
	
	public int getTopscore() {
		return topscore;
	}
	public void setTopscore(int topscore) {
		this.topscore = topscore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public List<String> getUsers() {
		return users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 默认方法
	 * @return
	 */
	public String execute(){
		try{
			topscore = g2048Service.findTop(openId);
			Map<String, Object> map = g2048Service.readBoard(openId);
			board = map.get("2048_board").toString();
			score = (Integer) map.get("2048_nowscore");
		}catch (Exception e){
			
		}
		return "success";
	}
	
	public String welcome(){
		if(openId==null||"".equals(openId)){
			return "noOpenId";
		}
		try{
			name = g2048Service.findName(openId);
		}catch (Exception e){
			
		}
		return "success";
	}
	
	public void editTop(){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("top", topscore);
			map.put("openId", openId);
			g2048Service.editTop(map);
		}catch (Exception e){
			
		}
	}
	public void saveBoard(){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("openId", openId);
			map.put("board", board);
			map.put("score", score);
			g2048Service.saveBoard(map);
		}catch (Exception e){
			
		}
	}
	public void deleteBoard(){
		try{
			g2048Service.deleteBoard(openId);
		}catch (Exception e){
			
		}
	}
	public String chartsList(){
		try{
			users = g2048Service.chartsList();
		}catch (Exception e){
			
		}
		return "success";
	}
}
