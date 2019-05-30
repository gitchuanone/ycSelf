package com.campus.service.implement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.dao.ActivityHoldDao;
import com.campus.model.ActUser;
import com.campus.model.Activity;
import com.campus.service.ActivityHoldService;
@Service
public class ActivityHoldServiceImplement implements ActivityHoldService {
	@Autowired
	private ActivityHoldDao  actDao;

	@Override
	public List<Activity> showAllCouldSignAct(String activityUsername) {
		return  actDao.showAllCouldSignAct(activityUsername);
	}

	@Override
	public void modifyActivityImpltimeByFirstOneSign(Integer activityId) {
		actDao.modifyActivityImpltimeByFirstOneSign(activityId);
	}

	@Override
	public Date judgeActivityIlpltimeIsExistByActid(Integer activityId) {
		return actDao.judgeActivityIlpltimeIsExistByActid(activityId);
	}

	@Override
	public Integer findUseridByUsername(String userName) {
		return actDao.findUseridByUsername(userName);
	}

	@Override
	public ActUser findUserHaveApplyAdvanceActivityByActidAndUserid(Integer actId2, Integer userId2) {
		return actDao.findUserHaveApplyAdvanceActivityByActidAndUserid(actId2, userId2);
	}

	@Override
	public void saveSpotSignInfo(Integer actId2, Integer userId2) {
		actDao.saveSpotSignInfo(actId2, userId2);
	}

	@Override
	public void modifyUserHaveApplyAdvanceActivityActUserByActidAndUserid(Integer actId2, Integer userId2) {
		actDao.modifyUserHaveApplyAdvanceActivityActUserByActidAndUserid(actId2, userId2);
	}

	@Override
	public ActUser judgeUserHaveEndJoin(Integer actId2, Integer userId2) { 
		return actDao.judgeUserHaveEndJoin(actId2, userId2);
				
	}

	@Override
	public void modifyActivityRealjoinAddOne(Integer activityId) {
		actDao.modifyActivityRealjoinAddOne(activityId);
	}
	/*
	 * ========================================================
	 */
	@Override
	public List<Activity> showAllCouldRemarkAct(Integer userId2) {
		return actDao.showAllCouldRemarkAct(userId2);
	}

	@Override
	public void actEndModifyEndtime(Integer activityId) {
		actDao.actEndModifyEndtime(activityId);
		
	}

	@Override
	public void startActUserPingfen(Integer actId2) {
		actDao.startActUserPingfen(actId2);
	}

	@Override
	public List<Integer> showBrokenPromises() {
		return actDao.showBrokenPromises();
	}

	@Override
	public List<Integer> showKeepPromises() {
		return actDao.showKeepPromises();
	}

	@Override
	public void reduceScoreOneByUserid(Integer userId) {
		actDao.reduceScoreOneByUserid(userId);
	}

	@Override
	public void addScoreOneByUserid(Integer userId) {
		actDao.addScoreOneByUserid(userId);
		
	}

	@Override
	public void saveUserRemarkActScores(ActUser actuser) {
		actDao.saveUserRemarkActScores(actuser);
	}

	@Override
	public void modifyActUserActpingfenStatusToClose(ActUser actuser) {
		actDao.modifyActUserActpingfenStatusToClose(actuser);
	}

	@Override
	public void modifyByActUserAvgToActivityInnovatescore(Integer actId2) {
		actDao.modifyByActUserAvgToActivityInnovatescore(actId2);
	}

	@Override
	public void modifyByActUserAvgToActivityExecutescore(Integer actId2) {
		actDao.modifyByActUserAvgToActivityExecutescore(actId2);
	}

	@Override
	public void modifyByActUserAvgToActivityProcessscore(Integer actId2) {
		actDao.modifyByActUserAvgToActivityProcessscore(actId2);
	}

	@Override
	public Double getThreeScoresAvgFromActivity(Integer actId2) {
		return actDao.getThreeScoresAvgFromActivity(actId2);
	}

	@Override
	public void saveThreeScoresAvgToActivityFinallScore(Double finallyScore,Integer actId2) {
		actDao.saveThreeScoresAvgToActivityFinallScore(finallyScore,actId2);
	}
	
	
	
	
}
