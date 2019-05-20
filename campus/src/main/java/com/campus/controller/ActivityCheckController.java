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
@RequestMapping("/activity-check/")
public class ActivityCheckController {
	@Autowired
	private ActivityService actService;
	
	/** #审核员审查自己院里管理员申请的活动,结果集展示 /activity-check/check-act-show
	 */
	@RequestMapping("check-act-show")
	@ResponseBody
	public List<Activity>  checkSelfCollegeApplyActivity(HttpSession session){
		User user=(User) session.getAttribute("user");
		String userCollege=user.getUserCollege();
		List<Activity> list;
		if(("校级").equals(userCollege)) {
			list=actService.checkAllApplyActivityUserlevel3();
		}else {
			list=actService.checkSelfCollegeApplyActivity(userCollege);
		}
		return list;
	}

	/**
	 * 审核员审核申请,同意请求;   /activity-check/aggreActivityCheckGoing
	 */
	@RequestMapping("aggreActivityCheckGoing")
	@ResponseBody
	public AjaxResult aggreActivityCheckGoing(Integer activityId) {
		try {
			actService.aggreActivityCheckGoing(activityId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	/**
	 * 审核员审核申请,拒绝请求;   /activity-check/refuseActivityCheckGoing
	 */
	@RequestMapping("refuseActivityCheckGoing")
	@ResponseBody
	public AjaxResult refuseActivityCheckGoing(Integer activityId) {
		try {
			actService.refuseActivityCheckGoing(activityId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
}
