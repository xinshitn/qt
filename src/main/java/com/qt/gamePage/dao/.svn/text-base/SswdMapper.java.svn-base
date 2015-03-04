package com.qt.gamePage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 谁是卧底数据库操作
 * @author VicenTN
 *
 */
@Repository
public interface SswdMapper {
	/**
	 * 查询昵称
	 * @param openId
	 * @return
	 */
	String findName(@Param("openId")String openId);
	
	/**
	 * 新增昵称
	 * @param map
	 */
	void addName(Map<String,String> map);
	
	/**
	 * 修改昵称
	 * @param map
	 */
	void editName(Map<String,String> map);
	
	/**
	 * 查询房间人数
	 * @param roomNum
	 * @return
	 */
	String countRoomPeople(@Param("roomNum")String roomNum);
	
	/**
	 * 创建房间
	 * @param map 传入number,password,owner
	 */
	void buildRoom(Map<String,String> map);
	
	/**
	 * 查询房主-返回昵称
	 * @param roomNum
	 * @return
	 */
	String roomOwner(@Param("roomNum")String roomNum);
	
	/**
	 * 查询房主-返回openId
	 * @param roomNum
	 * @return
	 */
	String roomOwnerId(@Param("roomNum")String roomNum);
	
	/**
	 * 修改房间房主
	 * @param map 传入openId,roomNum
	 */
	void editRoomOwner(Map<String,String> map);
	
	/**
	 * 修改玩家所在房间
	 * @param map
	 */
	void editUserRoom(Map<String,String> map);
	
	/**
	 * 查询所在房间号
	 * @param roomNum
	 * @return
	 */
	String findRoomNum(@Param("openId")String openId);
	
	/**
	 * 查询房间人员列表
	 * @param roomNum
	 * @return
	 */
	List<Map<String, String>> findUsers(@Param("roomNum")String roomNum);
	
	/**
	 * 查询房间密码
	 * @param roomNum
	 * @return
	 */
	String findRoomPassword(@Param("roomNum")String roomNum);
	
	/**
	 * 向玩家添加序号和抽中的词
	 * @param map
	 */
	void addCard(Map<String,String> map);
	
	/**
	 * 根据序号取词
	 * @param index
	 * @return
	 */
	List<Map<String, String>> catchCard(@Param("index")String index);
	
	/**
	 * 当前抽中的卡片
	 * @param openId
	 * @return
	 */
	String myCard(@Param("openId")String openId);
	
	/**
	 * 当前抽中卡片顺序号
	 * @param openId
	 * @return
	 */
	String myCardIndex(@Param("openId")String openId);
	
	/**
	 * 检查卡片序号
	 * @param openId
	 * @return
	 */
	String checkCardNum(@Param("openId")String openId);
	
	/**
	 * 解散房间
	 * @param roomNum
	 * @return
	 */
	void outRoomAll(@Param("roomNum")String roomNum);
	
	/**
	 * 退出房间
	 * @param openId
	 * @return
	 */
	void outRoomOne(@Param("openId")String openId);
	
	/**
	 * 删除房间
	 * @param roomNum
	 */
	void deleteRoom(@Param("roomNum")String roomNum);
}
