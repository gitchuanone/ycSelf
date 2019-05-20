package com.campus.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.AjaxResult;
import com.campus.model.Activity;
import com.campus.service.ActivityService;

@Controller
@RequestMapping("/activity/")
public class ShowActivityController {
	@Autowired
	private ActivityService actService;
	/**
	 * 测试路径
	 * @return
	 */
	@RequestMapping("partPage")
	public String partPage(){
		return "part-page";
	}
	/**
	 * /activity/show/showAllAct
	 */
	@RequestMapping("show/showAllAct")
	@ResponseBody
	public AjaxResult showAllActivity(HttpServletRequest request) {
		List<Activity> actList=actService.showAllActivity();
		return AjaxResult.oK(actList);
	}
	/**
	 * 预估计时间大于当天,并且公布时间距离当前时刻小于72小时;  /activity/show/predact
	 */
	@RequestMapping("show/predact")
	@ResponseBody
	public List<Activity> showPredtimeThanToday(HttpServletRequest request) {
		List<Activity> actList=actService.showPredtimeThanTodayProtimeLess3Day();
//		List<Activity> actList=actService.showAllActivity();
		return actList;
	}
	/**
	 * 3天审核过程展示展示;  /activity/show/checkresult
	 * @return
	 */
	@RequestMapping("show/checkresult")
	@ResponseBody
	public List<Activity> showCheckResultBy3day(){
		List<Activity> list=actService.showCheckResultBy3day();
		return list;
	}
	
	/**
	 * 2周前的举行过的活动结果展示 /activity/show/holdresult
	 * @return
	 */
	@RequestMapping("show/holdresult")
	@ResponseBody
	public List<Activity> showHoldResultLessThanDays(){
		List<Activity> list=actService.showHoldResultLessThanDays();
		return list;
	}
	/**
	 * 3天过审核结果展示;  /activity/show/checkendresult
	 * @return
	 */
	@RequestMapping("show/checkendresult")
	@ResponseBody
	public List<Activity> showCheckEndResultActivity(){
		List<Activity> list=actService.showCheckEndResultActivity();
		return list;
	}
	
	
}
