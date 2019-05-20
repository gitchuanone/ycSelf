package com.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.AjaxResult;
import com.campus.model.Activity;
import com.campus.model.User;
import com.campus.service.ActivityService;

@Controller
@RequestMapping("/activity-apply/")
public class ActivityApplyController {
	@Autowired
	private ActivityService actService;
	
	
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
	 * 用户预参与活动时的人数统计;   /activity-apply/userApplyPredJoin
	 */
	@RequestMapping("userApplyPredJoin")
	@ResponseBody
	public AjaxResult userApplyPredJoin(Integer clickCount,Integer activityId) {
		try {
			actService.userClickApplyActivityCountPredjoin(clickCount, activityId);
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
