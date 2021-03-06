package com.campus.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campus.model.ActUser;
import com.campus.model.Activity;

/**
 * 活动进行时dao接口层
 * @author Administrator
 */
public interface ActivityHoldDao {
	/**#展示所有自身可以进行签到的活动*/
	List<Activity>  showAllCouldSignAct(String  activityUsername);
	/**
	 * ------签到----
	 */
	/**#当第一个人签到时,表示活动已经进行了;修改活动开始执行时间*/
	 void modifyActivityImpltimeByFirstOneSign(Integer activityId);
	 /**#判断活动执行时间是否为空*/
	 Date judgeActivityIlpltimeIsExistByActid(Integer activityId);
	 /**#先通过用户账号值查询用户id值 */
	 Integer findUseridByUsername(String userName);
	 /**#通过用户id和活动id共同判断用户是否有预参与过 */
	 ActUser findUserHaveApplyAdvanceActivityByActidAndUserid(@Param("actId2")Integer actId2,@Param("userId2")Integer userId2);
	 /**#若没有参与过预参与,则插入新数据; 设置为已经参与过预参与*/
	 void saveSpotSignInfo(@Param("actId2")Integer actId2,@Param("userId2")Integer userId2);
	/** #如已经参与过预参与,则改变实际参与状态 */
	 void modifyUserHaveApplyAdvanceActivityActUserByActidAndUserid(@Param("actId2")Integer actId2,@Param("userId2")Integer userId2);
	 /**#判断用户是否已经参与过签到*/
	 ActUser judgeUserHaveEndJoin(@Param("actId2")Integer actId2,@Param("userId2")Integer userId2);
	 /**#签到成功,活动实际参与人数加1*/
	 void modifyActivityRealjoinAddOne(Integer activityId);
	 
	//======================活动结束===================
	 
	 /**#结束活动,更改结束时间*/
	 void actEndModifyEndtime(Integer activityId);
	 /** #开启用户活动中活动评分标识 */
	 void startActUserPingfen(Integer actId2);
	 
	/**#失信用户*/
	List<Integer> showBrokenPromises();
	/**#诚信用户,现场报道也可*/
	List<Integer> showKeepPromises();
	/**#失信用户,信用分减一*/
	void reduceScoreOneByUserid(Integer userId);
	/**#失信用户,信用分加一*/
	void addScoreOneByUserid(Integer userId);
	 
	//====================活动评分===============================
	
	 /**#显示可以平分活动的具体信息 */
	 List<Activity> showAllCouldRemarkAct(Integer userId2);
	/**#根据用户id和活动id值; 存入用户对活动创意;执行力;流程的评分*/
	 void saveUserRemarkActScores(ActUser actuser);
	 /**#评价完后活动,改变活动开启状态为关闭状态*/
	 void modifyActUserActpingfenStatusToClose(ActUser actuser);
	 /**#直接修改活动表中的得分; 创意得分*/
	 void modifyByActUserAvgToActivityInnovatescore(Integer  actId2);
	 /** #直接修改活动表中的得分;  执行力得分*/
	 void modifyByActUserAvgToActivityExecutescore(Integer  actId2);
	 /** #直接修改活动表中的得分;  流程得分*/
	 void modifyByActUserAvgToActivityProcessscore(Integer  actId2);
	 /**#根据前三项得分,计算出总分的平均分*/
	 Double getThreeScoresAvgFromActivity(Integer  actId2);
	 /** #得到平均分,在进行最终分的赋值*/
	 void saveThreeScoresAvgToActivityFinallScore(@Param("finallyScore") Double finallyScore,@Param("actId2") Integer actId2);
	 
	 
}
