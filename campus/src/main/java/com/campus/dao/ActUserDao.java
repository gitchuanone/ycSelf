package com.campus.dao;

import org.apache.ibatis.annotations.Param;

import com.campus.model.ActUser;

public interface ActUserDao {
	/**#判断联合表中是否存在活动用户数据*/
	ActUser actUserIsExitByActidUserid(@Param("actId2") Integer actId2,@Param("userId2") Integer userId2);
	/**用户第一次参加设置初值*/
	void  actUserFirstClickApplyAdvance(@Param("actId2") Integer actId2,@Param("userId2") Integer userId2);
	/**#联合主键表,用户参与预参与活动*/
	void actUserAttendAdvanceApplyAct(@Param("actId2") Integer actId2,@Param("userId2") Integer userId2);
	/**#联合主键表,用户退出预参与活动*/
	void actUserExitAdvanceApplyAct(@Param("actId2") Integer actId2,@Param("userId2") Integer userId2);
	/**#根据活动用户的两个主键查询是否参与预参与活动*/
	Integer  actUserShowUserapplyjoinByUseridActid(@Param("actId2") Integer actId2,@Param("userId2") Integer userId2);
	
	
}
