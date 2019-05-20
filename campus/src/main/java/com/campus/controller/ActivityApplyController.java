package com.campus.controller;

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
import com.campus.service.ActUserService;
import com.campus.service.ActivityService;

@Controller
@RequestMapping("/activity-apply/")
public class ActivityApplyController {
	@Autowired
	private ActivityService actService;
	@Autowired
	private ActUserService actUserService; 
	
	/*  预参与活动申请   /activity-apply/advanceapply 
	 * # activityUsername activityTheme activityOrgcollege activityOrganizer activityDescription activityPredtime
	 */
	@RequestMapping("advanceapply")
	@ResponseBody
	public AjaxResult applyAdvanceActivityByUsername(Activity activity) {
		try {
			actService.applyAdvanceActivityByUsername(activity);
		} catch (Exception e) {
			return AjaxResult.error("数据插入失败!");
		}
		return AjaxResult.oK();
	}
	/**
	 * 自己预参与申请信息展示;   /activity-apply/show-predact
	 */
	@RequestMapping("show-predact")
	@ResponseBody
	public List<Activity>  showSelfApplyAdvanceActivity(HttpSession session){
		User user=(User) session.getAttribute("user");
		String activityUsername=user.getUserName();
		List<Activity> list=actService.showSelfApplyAdvanceActivity(activityUsername);
		return list;
	}
	/**
	 * 自己申请的活动结果集展示/activity-apply/show-checkact
	 */
	@RequestMapping("show-checkact")
	@ResponseBody
	public List<Activity>  showSelfApplyCheckActivity(HttpSession session){
		User user=(User) session.getAttribute("user");
		String activityUsername=user.getUserName();
		List<Activity> list=actService.showSelfApplyCheckActivity(activityUsername);
		return list;
	}
	
	/**
	 * 用户参与预参与活动时的人数统计;   /activity-apply/userApplyPredJoin
	 */
	@RequestMapping("userApplyPredJoin")
	@ResponseBody
	public AjaxResult userApplyPredJoin(Integer activityId,HttpSession session) {
		try {
			//获取session中用户主键id值
			User user=(User) session.getAttribute("user");
			Integer userId=user.getUserId();
			//判断联合表中是否存在数据
			ActUser actUser=actUserService.actUserIsExitByActidUserid(activityId, userId);
			if(actUser==null) {
				//如果没有数据则初始化数据
				actUserService.actUserFirstClickApplyAdvance(activityId, userId);
			}
			//先判断用户是否已经参与了预报名
			Integer userApply=actUserService.actUserShowUserapplyjoinByUseridActid(activityId, userId);
			if(userApply==1) {
				return AjaxResult.error("您已经参与过了!");
			}
			
			//在活动人数统计中 +1
			Integer total=1;
			actService.userClickApplyActivityCountPredjoin(total, activityId);
			//将用户活动参与情况设置为 1 ;表示已经参与过
			actUserService.actUserAttendAdvanceApplyAct(activityId, userId);
		} catch (Exception e) {
			return AjaxResult.error("点击操作失败!");
		}
		return AjaxResult.oK();
	}
	
	
	
	/**
	 * 用户退出预参与活动时的人数统计;   /activity-apply/userExitPredJoin
	 */
	@RequestMapping("userExitPredJoin")
	@ResponseBody
	public AjaxResult userExitPredJoin(Integer activityId,HttpSession session) {
		try {
			//获取session中用户主键id值
			User user=(User) session.getAttribute("user");
			Integer userId=user.getUserId();
			//先判断用户是否已经参与了预报名
			Integer userApply=actUserService.actUserShowUserapplyjoinByUseridActid(activityId, userId);
			if(userApply!=1) {
				return AjaxResult.error("您还未参加!");
			}
			//在活动人数统计中 -1
			Integer total=-1;
			actService.userClickApplyActivityCountPredjoin(total, activityId);
			//将用户活动参与情况设置为  0 ;表示已经退出
			actUserService.actUserExitAdvanceApplyAct(activityId, userId);
		} catch (Exception e) {
			return AjaxResult.error("点击操作失败!");
		}
		return AjaxResult.oK();
	}
	
	
	
	/**
	 * 删除自己写的活动预参与信息;  /activity-apply/deleteSelfApplyAdvanceActicity
	 */
	@RequestMapping("deleteSelfApplyAdvanceActicity")
	@ResponseBody
	public AjaxResult deleteSelfApplyAdvanceActicity(Integer activityId) {
		try {

			actService.deleteSelfApplyAdvanceActicity(activityId);
		} catch (Exception e) {
			return AjaxResult.error("删除失败!");
		}
		return AjaxResult.oK();
	}
	/***
	 * 修改自己写的预申请活动信息     /activity-apply/updateSelfApplyAdvanceActicity
	 */
	@RequestMapping("updateSelfApplyAdvanceActicity")
	@ResponseBody
	public AjaxResult  updateSelfApplyAdvanceActicity(Activity activity) {
		try {
			actService.updateSelfApplyAdvanceActicity(activity);
		} catch (Exception e) {
			return AjaxResult.error("修改失败");
		}
		return  AjaxResult.oK();
	}
	
	
}
