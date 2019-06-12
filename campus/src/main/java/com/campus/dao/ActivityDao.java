package com.campus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campus.model.ActFile;
import com.campus.model.Activity;
public interface ActivityDao {
	/**查询所有的活动信息,主题不为空*/
	public List<Activity> showAllActivity();
	/**得到总的记录数*/
	Integer getTotal(Activity act);
	/**预估计时间大于当天,并且公布时间距离当前时刻小于2天 */
	List<Activity> showPredtimeThanTodayProtimeLess3Day();
	/***#当天日期超过公布日期2天且在3天内,审核状态公布出来*/
	List<Activity>  showCheckResultBy3day();
	/**#审核通过,活动进行时间在前2周内的活动展示出来*/
	List<Activity>  showHoldResultLessThanDays();
	/**#当天超过公布日期3天,且未超过预计时间,且活动未提前进行,结果展示*/
	List<Activity> showCheckEndResultActivity();
	/**#点击参加;用户参与预参与人数统计*/
	void userClickApplyActivityCountPredjoin(@Param("clickCount") Integer clickCount,@Param("activityId")Integer activityId);
	
	
	//==================申请=======================
	/** 预申请活动插入数据 
	 * insert into activity(activity_theme,activity_orgcollege,activity_organizer,activity_description,activity_predtime,activity_username,activity_processtime)  
		  values(#{activityTheme},#{activityOrgcollegeInfo},#{activityOrganizer},#{activityDescription},#{activityPredtime},#{activityUsername},now())
	 * */
	void applyAdvanceActivityByUsername(Activity activity);
	/*void applyAdvanceActivityByUsername(@Param("activityTheme") String activityTheme,@Param("activityOrgcollegeInfo") String activityOrgcollegeInfo,
			@Param("activityOrganizer") String activityOrganizer,@Param("activityDescription") String activityDescription,
			@Param("activityPredtime") Date activityPredtime,@Param("activityUsername") String activityUsername);*/
	/** 自己参与的预参与申请信息展示*/
	List<Activity>  showSelfApplyAdvanceActivity(String activityUsername);
	/**#展示自己参与到的预参与申请活动信息*/
	List<Activity> showSelfApplyCheckActivity(String activityUsername);
	/**#根据活动id删除自己写的预参与活动信息 */
	void deleteSelfApplyAdvanceActicity(@Param("activityId") Integer activityId);
	/**修改自己写的预申请活动信息*/
	void updateSelfApplyAdvanceActicity(Activity activity);
	
	//=====活动申请文件上传=====
	/**#申请活动;修改活动申请状态值 */
	void uploadApplyActivityApplystatusByActivityId(Integer activityId);
	/**#文件上传前判断是否有记录*/
	ActFile isFileExitByActiviyId(Integer activityId);
	/**#第一次上传文件,无记录,新插入基本信息 */
	void saveNewUploadFileInfo(ActFile actfile);
	/**#再次上传文件,将文件信息修改保存到act_fil表中*/
	void modifyActivityfileInfoByActivityId(ActFile actfile);
	/**#申请文件上传后,将名称存入活动表中*/
	void modifyApplyFilenameWhenUploadFile(Activity activity);
	
	
	//=========已通过活动地址的添加=============
	/**#展示可添加活动地址的活动;  自己申请被通过;还未进行的活动 */
	List<Activity> showCouldAddActivityAddr(String activityUsername);
	/**#通过活动id修改活动地址 */
	void addActivityAddr(@Param("activityPlace") String activityPlace,@Param("activityId")Integer activityId );
	
	
	//====================审核======================
	/**#审核员审查自己院里管理员申请的活动,结果集展示*/
	List<Activity> checkSelfCollegeApplyActivity(String userCollege);
	/** #如果为超级管理员,可以查看所有的信息*/
	List<Activity>  checkAllApplyActivityUserlevel3();
	/**#s审核信息,同意 */
	void aggreActivityCheckGoing(Integer activityId);
	/**#s审核信息,拒绝 */
	void refuseActivityCheckGoing(Integer activityId);
	
	/**#展示教师执行过的审批*/
	List<Activity> teacherCheckActResultShow(String userCollege);
	
	
	
	
}
