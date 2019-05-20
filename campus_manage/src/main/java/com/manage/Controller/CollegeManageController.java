package com.manage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.common.AjaxResult;
import com.manage.model.College;
import com.manage.service.CollegeManageService;

/**
 * 学院信息管理接口层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/college/")
public class CollegeManageController {
	@Autowired
	private CollegeManageService collegeService;
	
	/**
	 * 显示所有学院信息;    /college/showAllCollege
	 * @return
	 */	
	@RequestMapping("showAllCollege")
	@ResponseBody
	public List<College> showAllCollege(){
		List<College>  list=collegeService.showAllCollege();
		return list;
	}
	
	/***
	 *   删除学院信息;   /college/removeCollegeByCollegeId
	 */
	@RequestMapping("removeCollegeByCollegeId")
	@ResponseBody
	public AjaxResult removeCollegeByCollegeId(Integer collegeId) {
		try {
			collegeService.removeCollegeByCollegeId(collegeId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	/***
	 *  新增学院信息;   /college/addNewCollege
	 */
	@RequestMapping("addNewCollege")
	@ResponseBody
	public AjaxResult addNewCollege(String collegeName) {
		try {
			collegeService.addNewCollege(collegeName);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
}
