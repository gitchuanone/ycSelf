package com.manage.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.common.AjaxResult;
import com.manage.model.Manage;
import com.manage.service.ManageService;
/**
 * 管理员接口层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manage/")
public class ManageController {
	@Autowired
	private ManageService manageService;
	/**
	 * 测试web链接
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public String hellDemo() {
		return "hello world";
	}
	/**
	 * 管理员登录 ;  /manage/login
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public AjaxResult manageLogin(String name,String password,HttpSession session) {
		try {
			//用户对输入的的信息进行判断
			String magName=name.trim();
			String magPassword=password.trim();
			if("".equals(magName) || "".equals(magPassword)) {
				return AjaxResult.error("账户或密码不能为空!");
			}
			Manage manage=manageService.findManageIsexistByNamePwd(magName, magPassword);
			if(manage==null) {
				return AjaxResult.error("账户或密码错误!");
			}
			session.setAttribute("manage", manage);
			session.setMaxInactiveInterval(60*60*60*1);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("账户或密码错误!");
		}
		return AjaxResult.oK();
	}
	
	/**
	 *  登出;   /manage/logout
	 * @return
	 */
	@RequestMapping("logout")
	public String manageLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.html";
	}
	
	
	/***
	 *   展示所有用户;   /manage/showAllManage
	 */
	@RequestMapping("showAllManage")
	@ResponseBody
	public List<Manage> showAllManage(){
		List<Manage> list=manageService.showAllManage();
		return list;
	}
	
	/***
	 *   删除管理员;   /manage/removeManageByManageId
	 */
	@RequestMapping("removeManageByManageId")
	@ResponseBody
	public AjaxResult removeManageByManageId(Integer magId){
		try {
			//第一个不能删除
			if(magId==1) {
				return AjaxResult.error("该用户不可操作!");
			}
			manageService.removeManageByManageId(magId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	/***
	 *   新增管理员;   /manage/addNewManage
	 */
	@RequestMapping("addNewManage")
	@ResponseBody
	public AjaxResult addNewManage(Manage manage){
		try {
			manageService.addNewManage(manage);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	
}
