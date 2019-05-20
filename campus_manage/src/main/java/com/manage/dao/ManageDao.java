package com.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.manage.model.Manage;

public interface ManageDao {
	/**根据用户账号和密码查询用户*/
	Manage findManageIsexistByNamePwd(@Param("magName")String magName,@Param("magPassword")String magPassword);
	/**查询所有的管理员信息*/
	List<Manage> showAllManage();
	/**删除管理员*/
	void removeManageByManageId(@Param("magId") Integer  magId);
	/**新增管理员*/
	void addNewManage(Manage manage);
}
