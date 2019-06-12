package com.campus.service;


import java.util.List;

import com.campus.model.Activity;
import com.campus.model.User;

public interface UserService {
	/**查询所有自己学院学生信息*/
	public List<User> showAllSelfCollegeUser(String userCollege);
	/**根据用户账户和密码查询用户*/
	User showUserInfoByNamePwd(String userName,String userPassword);
	/**插入新的信息*/
	void saveNewUserInfo(String userName,String userPwd,String userNickname);
	/**查询用户是否存在*/
	Integer findUserIsexitByUsername(String userName);
	/**根据用户账号修改用户信息*/
	void modifyUserInfoByUsername(User user);
	/**  根据用户账号查询用户信息*/
	User showUserByUsername(String userName);
	/**手动注入数据*/
	void manualSaveNewUserInfo(String userName,String userPwd,String userNickname,String userCollege );
	//=======================================
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
