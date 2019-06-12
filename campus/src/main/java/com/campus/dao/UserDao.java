package com.campus.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campus.model.Activity;
import com.campus.model.User;

public interface UserDao {
	/** 查询所有自己学院的学生
	 * select  *  from user  where  user_college=#{userCollege}
	 * */
	public List<User> showAllSelfCollegeUser(String userCollege);
	
	/**根据账户和密码查询
	 * select  *  from user  where  user_name=#{userName}  and  user_password=#{userPwd}
	 * */
	public User showUserByNamePwd(@Param("userName") String userName,@Param("userPwd") String userPassword);
	
	/**插入新的信息
	 * */
	void  saveNewUserInfo(@Param("userName")String userName,@Param("userPwd") String userPwd,@Param("userNickname")String userNickname);
	
	/**根据账户查询用户的user_id
	 * SELECT user_id from user where user_name=#{userName}
	 * */
	Integer findUserIsexitByUsername(String userName);
	
	/**根据用户账号修改用户信息
	 * update user set user_nickname=#{userNickname},user_age=#{userAge},user_sex=#{userSex},user_college=#{userCollege},user_description=#{userDescription}  where user_name=#{userName}
	 * */
	void modifyUserInfoByUsername(User user);
	
	/**  根据用户账号查询用户信息
	 * select * from user where user_name=#{userName}
	 */
	User showUserByUsername(String userName);
	/**手动注入数据*/
	void manualSaveNewUserInfo(@Param("userName")String userName,@Param("userPwd") String userPwd,@Param("userNickname")String userNickname,@Param("userCollege")String userCollege );
	//==============================================
	/**重置所有用户信用得分*/
	void resetAllUserscoreZero();
	/**#提升用户等级*/
	void riseUserlevel(Integer userId);
	/**#降低用户等级*/
	void downUserLevel(Integer userId);
	
	//===============
	/** #展示自己待参加活动详情 */
	List<Activity> showWaitJoinActivity(Integer userId2);
	
}
