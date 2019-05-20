package com.manage.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.ManageDao;
import com.manage.model.Manage;
import com.manage.service.ManageService;

@Service
public class ManageServiceImplement implements ManageService {
	@Autowired
	private ManageDao manageDao;
	
	@Override
	public Manage findManageIsexistByNamePwd(String magName, String magPassword) {
		Manage manage=manageDao.findManageIsexistByNamePwd(magName, magPassword);
		return manage;
	}

	@Override
	public List<Manage> showAllManage() {
		List<Manage> list=manageDao.showAllManage();
		return list;
	}

	@Override
	public void removeManageByManageId(Integer magId) {
		manageDao.removeManageByManageId(magId);
		
	}

	@Override
	public void addNewManage(Manage manage) {
		manageDao.addNewManage(manage);
		
	}

}
