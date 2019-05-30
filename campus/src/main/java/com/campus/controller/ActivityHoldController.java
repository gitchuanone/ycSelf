package com.campus.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.AjaxResult;
import com.campus.model.ActUser;
import com.campus.model.Activity;
import com.campus.model.User;
import com.campus.service.ActivityHoldService;
/**
 * 活动进行时controller接口层
 * @author Administrator
 */
@Controller
@RequestMapping("/activity-hold/")
public class ActivityHoldController {
	@Autowired
	private ActivityHoldService  holdService;
	
	/**
	 * 查询自身所有可进行签到的活动;     /activity-hold/showAllCouldSignAct
	 * @param session
	 * @return
	 */
	@RequestMapping("showAllCouldSignAct")
	@ResponseBody
	public List<Activity> showAllCouldSignAct(HttpSession session){
		//获取用户账户
		User user = (User) session.getAttribute("user");
		String userName=user.getUserName();
		//查询结果返回
		List<Activity> list= holdService.showAllCouldSignAct(userName);
		return list;
	}
	
	/***
	 * 进行手动签到;并改变活动执行时间;     /activity-hold/signByUserManual
	 * @return
	 */
	@RequestMapping("signByUserManual")
	@ResponseBody
	public AjaxResult signByUserManual(Integer actId2,String userName) {
		try {
			//1)通过账号查询用户id
			Integer userId2=holdService.findUseridByUsername(userName);
			if(userId2==null) {
				return AjaxResult.error("无此用户");
			}
			//2)首先判断用户是否已经签到
			ActUser actuser=holdService.judgeUserHaveEndJoin(actId2, userId2);
			if(actuser!=null) {
				return AjaxResult.error("已经进行过签到");
			}
			//3)判断用户是否为现场签到
			ActUser postJoin = holdService.findUserHaveApplyAdvanceActivityByActidAndUserid(actId2, userId2);
			if(postJoin==null) {
				//之前未参与过预参与活动;新插入记录并把参与状态改为'参与'
				holdService.saveSpotSignInfo(actId2, userId2);
			}else {
				holdService.modifyUserHaveApplyAdvanceActivityActUserByActidAndUserid(actId2, userId2);
			}
			//4)在判断活动是否有执行时间
			Date implTime=holdService.judgeActivityIlpltimeIsExistByActid(actId2);
			if(implTime==null || "".equals(implTime)) {
				//第一个人签到时代表活动进行时间的确立
				holdService.modifyActivityImpltimeByFirstOneSign(actId2);
			}
			//5)签到一人,在活动实际参与人数 +1
			holdService.modifyActivityRealjoinAddOne(actId2);
		} catch (Exception e) {
			return AjaxResult.error("出错了");
		}
		return AjaxResult.oK();
	}
	
	
	/**
	 * 根据活动id值,结束活动,同时开启活动评分; 	/activity-hold/endActByActid
	 * @return
	 */
	@RequestMapping("endActByActid")
	@ResponseBody
	public AjaxResult endActByActid(Integer actId2) {
		try {
			//互动结束,更改活动结束时间
			holdService.actEndModifyEndtime(actId2);
			//活动结束,开启活动评分
			holdService.startActUserPingfen(actId2);
			//统计预参与和实际参与的情况,进行相应的奖惩
			//1)失信用户
			List<Integer> brokenList=holdService.showBrokenPromises();
			if(brokenList!=null) {
				for (Integer userId : brokenList) {
					//失信用户信用分减一
					holdService.reduceScoreOneByUserid(userId);
				}
			}
			//2)守信用户
			List<Integer> keepList=holdService.showKeepPromises();
			if(keepList!=null) {
				//守信用户信用分加一
				for (Integer userId : keepList) {
					holdService.addScoreOneByUserid(userId);
				}
			}
		} catch (Exception e) {
			return AjaxResult.error();
		}
		return AjaxResult.oK();
	}
	
	
	/*
	public AjaxResult signByUserManual() {
		try {
			
		} catch (Exception e) {
			return AjaxResult.error();
		}
		return AjaxResult.oK();
	}
	*/
	//=============================活动评分=============================
	/***
	 * 展示所有自己可以参与评分的活动;	/activity-hold/showAllCouldRemarkAct
	 * @param session
	 * @return
	 */
	@RequestMapping("showAllCouldRemarkAct")
	@ResponseBody
	public List<Activity> showAllCouldRemarkAct(HttpSession session){
		//获取用户id
		User user=(User) session.getAttribute("user");
		Integer userId=user.getUserId();
		//查询结果返回
		return holdService.showAllCouldRemarkAct(userId);
	}
	
	/**
	 * 用户对活动的评分; 		/activity-hold/getScoresByStarts 	
	 * @return
	 */
	@RequestMapping("getScoresByStarts")
	@ResponseBody
	public AjaxResult getScoresByStarts(ActUser actuser,HttpSession session) {
		try {
			//获取用户id值
			User user = (User) session.getAttribute("user");
			Integer userId2=user.getUserId();
			//1)将前端获取到的值和session中取出的userid存入实体对象
			actuser.setUserId2(userId2);
			//2)将评分数据存入用户活动表中
			holdService.saveUserRemarkActScores(actuser);
			//3)将获取到的创意,执行力,流程得分存入用户活动表中
			Integer actId2=actuser.getActId2();
			holdService.modifyByActUserAvgToActivityInnovatescore(actId2);
			holdService.modifyByActUserAvgToActivityExecutescore(actId2);
			holdService.modifyByActUserAvgToActivityProcessscore(actId2);
			//3.2)在关闭评分按钮
			holdService.modifyActUserActpingfenStatusToClose(actuser);
			//4)由活动表的三种得分得到平均分;在存入活动表中
			Double finallyScore=holdService.getThreeScoresAvgFromActivity(actId2);
			holdService.saveThreeScoresAvgToActivityFinallScore(finallyScore, actId2);
		} catch (Exception e) {
			return AjaxResult.error();
		}
		return AjaxResult.oK();
	}
	
}
