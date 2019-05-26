package com.campus.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.dao.ActivityDao;
import com.campus.model.ActFile;
import com.campus.model.Activity;
import com.campus.service.ActivityService;
@Service
public class ActivityServiceImplement implements ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Override
	public List<Activity> showAllActivity() {
		List<Activity> list=activityDao.showAllActivity();
		return list;
	}
	@Override
	public Integer getTotal(Activity act) {
		Integer row=activityDao.getTotal(act);
		return row;
	}
	@Override
	public List<Activity> showPredtimeThanTodayProtimeLess3Day() {
		List<Activity> list=activityDao.showPredtimeThanTodayProtimeLess3Day();
		return list;
	}
	@Override
	public List<Activity> showCheckResultBy3day() {
		List<Activity> list=activityDao.showCheckResultBy3day();
		return list;
	}
	@Override
	public List<Activity> showHoldResultLessThanDays() {
		List<Activity> list=activityDao.showHoldResultLessThanDays();
		return list;
	}
	@Override
	public List<Activity> showCheckEndResultActivity() {
		List<Activity> list=activityDao.showCheckEndResultActivity();
		return list;
	}
	@Override
	public void applyAdvanceActivityByUsername(Activity activity) {
		activityDao.applyAdvanceActivityByUsername(activity);
	}
	/*@Override
	public void applyAdvanceActivityByUsername(String activityTheme,String activityOrgcollegeInfo,
			String activityOrganizer,String activityDescription,
			Date activityPredtime,String activityUsername) {
		activityDao.applyAdvanceActivityByUsername(activityTheme, activityOrgcollegeInfo, activityOrganizer, activityDescription, activityPredtime, activityUsername);
	};*/
	@Override
	public List<Activity> showSelfApplyAdvanceActivity(String activityUsername) {
		List<Activity> list=activityDao.showSelfApplyAdvanceActivity(activityUsername);
		return list;
	}
	@Override
	public List<Activity> showSelfApplyCheckActivity(String activityUsername) {
		List<Activity>  list=activityDao.showSelfApplyCheckActivity(activityUsername);
		return list;
	}
	@Override
	public List<Activity> checkSelfCollegeApplyActivity(String userCollege) {
		List<Activity> list=activityDao.checkSelfCollegeApplyActivity(userCollege);
		return list;
	}
	@Override
	public List<Activity> checkAllApplyActivityUserlevel3() {
		List<Activity> list=activityDao.checkAllApplyActivityUserlevel3();
		return list;
	}
	@Override
	public void userClickApplyActivityCountPredjoin(Integer clickCount, Integer activityId) {
		activityDao.userClickApplyActivityCountPredjoin(clickCount, activityId);
	}
	@Override
	public void deleteSelfApplyAdvanceActicity(Integer activityId) {
		activityDao.deleteSelfApplyAdvanceActicity(activityId);
	}
	@Override
	public void updateSelfApplyAdvanceActicity(Activity activity) {
		activityDao.updateSelfApplyAdvanceActicity(activity);
	}
	@Override
	public void aggreActivityCheckGoing(Integer activityId) {
		activityDao.aggreActivityCheckGoing(activityId);
	}
	@Override
	public void refuseActivityCheckGoing(Integer activityId) {
		activityDao.refuseActivityCheckGoing(activityId);
		
	}
	@Override
	public List<Activity> teacherCheckActResultShow(String userCollege) {
		List<Activity>  list=activityDao.teacherCheckActResultShow(userCollege);
		return list;
	}
	
	@Override
	public void uploadApplyActivityApplystatusByActivityId(Integer activityId) {
		activityDao.uploadApplyActivityApplystatusByActivityId(activityId);
		
	}
	@Override
	public ActFile isFileExitByActiviyId(Integer activityId) {
		ActFile actfile=activityDao.isFileExitByActiviyId(activityId);
		return actfile;
	}
	@Override
	public void saveNewUploadFileInfo(ActFile actfile) {
		activityDao.saveNewUploadFileInfo(actfile);
		
	}
	@Override
	public void modifyActivityfileInfoByActivityId(ActFile actfile) {
		activityDao.modifyActivityfileInfoByActivityId(actfile);
		
	}
	@Override
	public void modifyApplyFilenameWhenUploadFile(Activity activity) {
		activityDao.modifyApplyFilenameWhenUploadFile(activity);
		
	}
	


}
