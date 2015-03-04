package com.qt.gamePage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface G2048Mapper {
	/**
	 * 查询本人最高分
	 * @param operId
	 * @return
	 */
	int findTop(@Param("openId")String openId);
	
	/**
	 * 更新本人最高分
	 * @param map
	 */
	void editTop(Map<String, Object> map);
	
	/**
	 * 保存残局
	 * @param map
	 */
	void saveBoard(Map<String, Object> map);
	
	/**
	 * 读取残局
	 * @param openId
	 * @return
	 */
	Map<String, Object> readBoard(@Param("openId")String openId);
	
	/**
	 * 清零分数和残局
	 * @param openId
	 */
	void deleteBoard(@Param("openId")String openId);
	
	/**
	 * 查询排行榜
	 * @return
	 */
	List<Map<String, String>> chartsList();
	
	/**
	 * 查询昵称
	 * @param openId
	 * @return
	 */
	String findName(@Param("openId")String openId);
}
