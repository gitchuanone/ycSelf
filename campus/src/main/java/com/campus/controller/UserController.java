package com.campus.controller;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.AjaxResult;
import com.campus.model.Activity;
import com.campus.model.College;
import com.campus.model.User;
import com.campus.service.CollegeService;
import com.campus.service.UserService;
/**
 * 用户信息的登录,退出,查看,修改 
 * @author Administrator
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CollegeService collegeService;
	/**
	 * 2019-05-04 数据插入到512015 2000
	 * @return
	 */
//	@RequestMapping("/user/testManual")
//	@ResponseBody
	public String testManualInsetInto() {
		try {
			String name="理学院";
			for (int i=0;i<=1000;i++) {
				switch (i%16) {
				case 0:name="信息工程学院" ;break;
				case 1:name="环境与资源学院" ;break;
				case 2:name="材料科学与工程学院" ;break;
				case 3:name="制造科学与工程学院" ;break;
				case 4:name="土木工程与建筑学院" ;break;
				case 5:name="生命科学与工程学院" ;break;
				case 6:name="计算机科学与技术学院" ;break;
				case 7:name="国防科技学院" ;break;
				case 8:name="理学院" ;break;
				case 9:name="经济管理学院" ;break;
				case 10:name="法学院" ;break;
				case 11:name="文学与艺术学院" ;break;
				case 12:name="外国语学院" ;break;
				case 13:name="马克思主义学院" ;break;
				case 14:name="体育学科部" ;break;
				case 15:name="应用技术学院" ;break;
				default:break;
				};
				Integer aa=1002+i;
				String userName="512015"+aa;
				String userNickname=System.currentTimeMillis()+"";
				String pwd="123456";
				userService.manualSaveNewUserInfo(userName, pwd, userNickname, name);
//				System.out.println(userName+"\n"+userNickname+"\n"+name);
				System.out.println("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 用户登录,并想session域中存入账号,密码,学院所有信息
	 */
	@RequestMapping("login")
	public String UserLogin(String userName,String userPassword,HttpSession httpSession) {
		try {
			if(userName == null || userPassword==null) {
//				redirect:/index
				return "redirect:/index";
			}
			//去除输入字符串的首尾空格
			String name=userName.trim();
			String pwd=userPassword.trim();
			//判断输入的是否为空
			if("".equals(name) || "".equals(pwd)) {
				return "redirect:/index";
			}
			//查询数据库中是否存在用户
			User user=userService.showUserInfoByNamePwd(name, pwd);
			if(user == null){
				return "redirect:/index";
			}
			//将学院信息查出,放入session中
			List<College> collegeList=collegeService.showAllCollege();
			//将账户密码信息存入session中
			httpSession.setAttribute("user", user);
//			httpSession.setAttribute("userNickname", user.getUserNickname());
			httpSession.setAttribute("collegeList", collegeList);
			//设置session失效时间;60s*...
			httpSession.setMaxInactiveInterval(60*60*24);  //24小时
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeErrorException(null, "error");
		}
		return "redirect:/homepage";
	}
	
	/**
	 *用户注册,初次
	 */
	@RequestMapping("register")
	@ResponseBody
	@Transactional
	public AjaxResult UserRegister(String userName, String userPassword) {
		try {
			//去除收尾空字符串
			String name=userName.trim();
			String pwd=userPassword.trim();
			//判断参数合法性,如果不合法则退到注册页面
			Integer userId=userService.findUserIsexitByUsername(name);
			if(userId == null || userId == 0){
				String nickname=System.currentTimeMillis()+"用户";
				userService.saveNewUserInfo(name,pwd,nickname);
			}else {
				return AjaxResult.error("账户已存在!");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			return AjaxResult.error("注册失败!");
		}
		return AjaxResult.oK("注册成功!");
	}
	
	/**
	 *	用户退出;同时清除session中所有的值
	 */
	@RequestMapping("logout")
	public String UserLogout(HttpSession httpSession) {
		httpSession.invalidate();
		return "index";
	}
	
	/** 用户信息修改:userName  userNickname  userAge  userSex   userCollege   userDescription
	 * /user/modifyuser
	 */
	@RequestMapping("modifyuser")
	@ResponseBody
	public AjaxResult UserInfoModify(User userinfo,HttpSession httpSession) {
		try {
			userService.modifyUserInfoByUsername(userinfo);
			User user=userService.showUserByUsername(userinfo.getUserName());
			httpSession.setAttribute("user", user);
		} catch (Exception e) {
			return AjaxResult.error("修改失败!");
		}
		return AjaxResult.oK("修改成功!");
	}
	
	
	/**
	 * 管理所有的用户信息;      /user/manage-userinfo
	 */
	@RequestMapping("manage-userinfo")
	@ResponseBody
	public List<User> manageShowUserInfo(HttpSession session){
		//获取用户所属学院
		User user = (User) session.getAttribute("user");
		String collegeName=user.getUserCollege();
		List<User> list=userService.showAllSelfCollegeUser(collegeName);
		return list;
	}
	
	/**
	 * 重置所有用户信用得分;   /user/resetAllUserscoreZero
	 */
	@RequestMapping("resetAllUserscoreZero")
	@ResponseBody
	public AjaxResult resetAllUserscoreZero() {
		try {
			userService.resetAllUserscoreZero();
		} catch (Exception e) {
			return AjaxResult.error("操作失败");
		}
		return AjaxResult.oK();
	}
	
	/**
	 * 提升用户等级;   /user/riseUserlevel
	 */
	@RequestMapping("riseUserlevel")
	@ResponseBody
	public AjaxResult riseUserlevel(Integer userId) {
		try {
			userService.riseUserlevel(userId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败");
		}
		return AjaxResult.oK();
	}
	/**
	 * 降低用户等级;  /user/downUserLevel
	 */
	@RequestMapping("downUserLevel")
	@ResponseBody
	public AjaxResult downUserLevel(Integer userId) {
		try {
			userService.downUserLevel(userId);
		} catch (Exception e) {
			return AjaxResult.error("操作失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	//==============================
	
	/**
	 * 展示自己待参加的活动详情;	/user/showWaitJoinActivity
	 * @return
	 */
	@RequestMapping("showWaitJoinActivity")
	@ResponseBody
	public List<Activity>  showWaitJoinActivity(HttpSession session){
		User user=(User) session.getAttribute("user");
		return  userService.showWaitJoinActivity(user.getUserId());
	}
	
	
	
}
