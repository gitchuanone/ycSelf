package com.campus.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.dao.UserDao;
import com.campus.model.User;
import com.campus.service.UserService;
@Service
public class UserServiceImplement implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> showAllUser() {
		List<User> list=userDao.showAllUser();
		return list;
	}

	@Override
	public User showUserInfoByNamePwd(String userName, String userPassword) {
		User user=userDao.showUserByNamePwd(userName, userPassword);
		return user;
	}

	@Override
	public void saveNewUserInfo(String userName, String userPwd,String userNickname){
		userDao.saveNewUserInfo(userName, userPwd,userNickname);
	}

	@Override
	public Integer findUserIsexitByUsername(String userName) {
		Integer userId=userDao.findUserIsexitByUsername(userName);
		return  userId;
	}

	@Override
	public void modifyUserInfoByUsername(User user) {
		userDao.modifyUserInfoByUsername(user);
	}

	@Override
	public User showUserByUsername(String userName) {
		User user=userDao.showUserByUsername(userName);
		return user;
	}

	@Override
	public void manualSaveNewUserInfo(String userName, String userPwd, String userNickname, String userCollege) {
		userDao.manualSaveNewUserInfo(userName, userPwd, userNickname, userCollege);
	}

	@Override
	public void resetAllUserscoreZero() {
		userDao.resetAllUserscoreZero();
	}

	@Override
	public void riseUserlevel(Integer userId) {
		userDao.riseUserlevel(userId);
	}

	@Override
	public void downUserLevel(Integer userId) {
		userDao.downUserLevel(userId);
	}

}
