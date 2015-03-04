package com.qt.gamePage.service;

import java.util.List;
import java.util.Map;

public interface G2048Service {
	/**
	 * 查询本人最高分
	 * @param openId
	 * @return
	 */
	int findTop(String openId);
	
	/**
	 * 更新本人最高分
	 * @param map
	 */
	void editTop(Map<String, Object> map);
	
	/**
	 * 保存残局
	 * @param board
	 */
	void saveBoard(Map<String, Object> map);
	
	/**
	 * 读取残局
	 * @param openId
	 * @return
	 */
	Map<String,Object> readBoard(String openId);
	
	/**
	 * 清零分数和残局
	 * @param openId
	 */
	void deleteBoard(String openId);
	
	/**
	 * 获取排行榜
	 * @return
	 */
	public List<String> chartsList();
	
	/**
	 * 查询昵称
	 * @param openId
	 * @return
	 */
	public String findName(String openId);
}
