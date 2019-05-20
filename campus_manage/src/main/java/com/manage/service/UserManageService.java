package com.manage.service;

import java.util.Date;
import java.util.List;

import com.manage.model.User;

public interface UserManageService {
	/**查询所有的用户基本信息*/
	List<User> showAllUserInfo();
	/**提升用户等级 */
	void upUserLevel(Integer userId);
	/**降低用户等级 */
	void downUserLevel(Integer userId);
	/**重置用户信息 */
	void resetUserInfo(Integer userId);
	/**#重置所有信用分*/
	void resetAllUserScore();
	/**按创建时间查询*/
	 List<User> selectByFoundTime(String userFoundtime);
	 /**删除用户信息*/
	 void removeUserByUserId(Integer userId);
	 /**注册新用户*/
	 void saveNewUser(User user);
}
