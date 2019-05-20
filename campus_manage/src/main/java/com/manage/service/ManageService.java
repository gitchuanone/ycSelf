package com.manage.service;

import java.util.List;

import com.manage.model.Manage;

public interface ManageService {
	/**根据用户账号和密码查询用户是否存在*/
	Manage findManageIsexistByNamePwd(String magName,String magPassword);
	/**查询所有的管理员信息*/
	List<Manage> showAllManage();
	/**删除管理员*/
	void removeManageByManageId(Integer  magId);
	/**新增管理员*/
	void addNewManage(Manage manage);
}
