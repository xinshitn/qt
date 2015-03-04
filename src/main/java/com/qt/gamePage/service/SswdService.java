package com.qt.gamePage.service;

import java.util.List;
import java.util.Map;

public interface SswdService {
	/**
	 * 查询昵称
	 * @param openId
	 * @return
	 */
	public String findName(String openId);
	
	/**
	 * 新增昵称
	 * @param map
	 * @return
	 */
	public String addName(Map<String,String> map);
	
	/**
	 * 创建新房间
	 * @param password 房间密码
	 * @param openId
	 * @return
	 */
	public String buildRoom(String password, String openId);
	
	/**
	 * 查询所在房间号
	 * @param openId
	 * @return
	 */
	public String findRoomNum(String openId);
	
	/**
	 * 查询房间人员列表
	 * @param roomNum
	 * @return
	 */
	public List<String> findUsers(String roomNum);
	
	/**
	 * 统计房间人数
	 * @param roomNum
	 * @return
	 */
	public int countRoomNUm(String roomNum);
	
	/**
	 * 查询房间密码
	 * @param roomNum
	 * @return
	 */
	public String findRoomPassword(String roomNum);
	
	/**
	 * 查询房间人员列表（openId列表）
	 * @param roomNum
	 * @return
	 */
	public List<String> findUsersOpenId(String roomNum);
	
	/**
	 * 向玩家添加序号和抽中的词
	 * @param index 序号
	 * @param card 词汇
	 * @param openId
	 * @param cardNum 卡片序号
	 */
	public void addCard(String index, String card, String openId, String cardNum);
	
	/**
	 * 根据序号取词
	 * @param index
	 * @return
	 */
	public List<Map<String, String>> catchCard(String index);
	
	/**
	 * 当前抽中的卡片
	 * @param openId
	 * @return
	 */
	public String myCard(String openId);
	
	/**
	 * 当前抽中卡片顺序号
	 * @param openId
	 * @return
	 */
	public String myCardIndex(String openId);
	
	/**
	 * 玩家进入房间
	 * @param map
	 */
	public void enterRoom(Map<String,String> map);
	
	/**
	 * 查询房主openId
	 * @param roomNum
	 * @return
	 */
	public String roomOwner(String roomNum);
	
	/**
	 * 检查卡片序号
	 * @param openId
	 * @return
	 */
	public String checkCardNum(String openId);
	
	/**
	 * 解散房间
	 * @param roomNum
	 * @return
	 */
	public void outRoomAll(String roomNum);
	
	/**
	 * 退出房间
	 * @param openId
	 * @return
	 */
	public void outRoomOne(String openId);
}
