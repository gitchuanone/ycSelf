package com.campus.service;

import org.apache.ibatis.annotations.Param;

import com.campus.model.ActUser;

public interface ActUserService {
	/**#判断联合表中是否存在活动用户数据*/
	ActUser actUserIsExitByActidUserid(Integer actId2,Integer userId2);
	
	/**用户第一次参加设置初值*/
	void  actUserFirstClickApplyAdvance(Integer actId2,Integer userId2);
	
	/**#联合主键表,用户参与预参与活动*/
	void actUserAttendAdvanceApplyAct(Integer actId2,Integer userId2);
	/**#联合主键表,用户退出预参与活动*/
	void actUserExitAdvanceApplyAct(Integer actId2,Integer userId2);
	
	/**#根据活动用户的两个主键查询是否参与预参与活动*/
	Integer  actUserShowUserapplyjoinByUseridActid(Integer actId2, Integer userId2);
	
	
	
}
