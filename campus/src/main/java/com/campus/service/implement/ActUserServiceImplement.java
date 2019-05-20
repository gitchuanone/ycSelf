package com.campus.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.dao.ActUserDao;
import com.campus.model.ActUser;
import com.campus.service.ActUserService;

@Service
public class ActUserServiceImplement implements ActUserService {
	@Autowired
	private ActUserDao actUserDao;

	@Override
	public void actUserAttendAdvanceApplyAct(Integer actId2, Integer userId2) {
		actUserDao.actUserAttendAdvanceApplyAct(actId2, userId2);
		
	}

	@Override
	public Integer actUserShowUserapplyjoinByUseridActid(Integer actId2, Integer userId2) {
		Integer userApplyjoin=actUserDao.actUserShowUserapplyjoinByUseridActid(actId2, userId2);
		return userApplyjoin;
	}

	@Override
	public void actUserExitAdvanceApplyAct(Integer actId2, Integer userId2) {
		actUserDao.actUserExitAdvanceApplyAct(actId2, userId2);
		
	}

	@Override
	public void actUserFirstClickApplyAdvance(Integer actId2, Integer userId2) {
		actUserDao.actUserFirstClickApplyAdvance(actId2, userId2);
	}

	@Override
	public ActUser actUserIsExitByActidUserid(Integer actId2, Integer userId2) {
		ActUser actUser=actUserDao.actUserIsExitByActidUserid(actId2, userId2);
		return actUser;
	}
	
}
