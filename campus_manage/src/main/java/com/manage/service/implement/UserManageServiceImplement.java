package com.manage.service.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.UserDao;
import com.manage.model.User;
import com.manage.service.UserManageService;
@Service
public class UserManageServiceImplement implements UserManageService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> showAllUserInfo() {
		List<User> list=userDao.showAllUserInfo();
		return list;
	}

	@Override
	public void upUserLevel(Integer userId) {
		userDao.upUserLevel(userId);
		
	}

	@Override
	public void downUserLevel(Integer userId) {
		userDao.downUserLevel(userId);
		
	}

	@Override
	public void resetUserInfo(Integer userId) {
		userDao.resetUserInfo(userId);
		
	}

	@Override
	public void resetAllUserScore() {
		userDao.resetAllUserScore();
		
	}

	@Override
	public List<User> selectByFoundTime(String userFoundtime) {
		List<User> list=userDao.selectByFoundTime(userFoundtime);
		return list;
	}

	@Override
	public void removeUserByUserId(Integer userId) {
		userDao.removeUserByUserId(userId);
	}

	@Override
	public void saveNewUser(User user) {
		userDao.saveNewUser(user);
		
	}

	@Override
	public User showUserInfoByUserid(Integer userId) {
		return  userDao.showUserInfoByUserid(userId);
	}

}
