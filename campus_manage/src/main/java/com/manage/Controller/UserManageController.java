package com.manage.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.common.AjaxResult;
import com.manage.model.Manage;
import com.manage.model.User;
import com.manage.service.ManageService;
import com.manage.service.UserManageService;
/**
 * 用户信息管理接口层
 * @author Administrator
 */
@Controller
@RequestMapping("/user/")
public class UserManageController {
	@Autowired
	private UserManageService userMagService;
	
	/**
	 * 显示所有用户基本信息/user/showAllUser
	 */
	@RequestMapping("showAllUser")
	@ResponseBody
	public List<User> userManageShowAll(){
		List<User> list=userMagService.showAllUserInfo();
		return list;
	}
	
	/**
	 *重置所有信用分; /user/resetAllUserScore
	 */
	@RequestMapping("resetAllUserScore")
	@ResponseBody
	public AjaxResult resetAllUserScore(){
		try {
			userMagService.resetAllUserScore();
		} catch (Exception e) {
			return AjaxResult.error("重置失败!!!");
		}
		return AjaxResult.oK();
	}
	
	/**
	 * 升级用户等级;  /user/riseUserlevel
	 */
	@RequestMapping("riseUserlevel")
	@ResponseBody
	public AjaxResult upUserLevel(Integer userId){
		try {
			userMagService.upUserLevel(userId);
		} catch (Exception e) {
			return AjaxResult.error("提升等级失败!!!");
		}
		return AjaxResult.oK();
	}
	/**
	 *  降低用户等级;  /user/downUserLevel
	 */
	@RequestMapping("downUserLevel")
	@ResponseBody
	public AjaxResult downUserLevel(Integer userId){
		try {
			userMagService.downUserLevel(userId);
		} catch (Exception e) {
			return AjaxResult.error("降低等级失败!!!");
		}
		return AjaxResult.oK();
	}
	/**
	 *  重置用户信息;  /user/resetUserInfo
	 */
	@RequestMapping("resetUserInfo")
	@ResponseBody
	public AjaxResult resetUserInfo(Integer userId){
		try {
			userMagService.resetUserInfo(userId);
		} catch (Exception e) {
			return AjaxResult.error("重置失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	/**
	 *  按时间查询用户;  /user/selectByFoundTime
	 * @throws ParseException 
	 */
	@RequestMapping("selectByFoundTime")
	@ResponseBody
	public List<User> selectByFoundTime(String userFoundtime) throws ParseException{
		userFoundtime = userFoundtime.replace("GMT", "").replaceAll("\\(.*\\)", "");
	    //将字符串转化为date类型，格式2016-10-12
	    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
	    Date dateTrans = null;
	    String time=null;
	    try {
	        dateTrans = format.parse(userFoundtime);
	        time= new SimpleDateFormat("yyyy-MM-dd").format(dateTrans).replace("-","/");
	        System.out.println(time);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		System.out.println(time);
		List<User> list=userMagService.selectByFoundTime(time);
		return list;
	}
	
	
	/**
	 *  删除用户信息;  /user/removeUserByUserId
	 */
	@RequestMapping("removeUserByUserId")
	@ResponseBody
	public AjaxResult removeUserByUserId(Integer userId){
		try {
			userMagService.removeUserByUserId(userId);
		} catch (Exception e) {
			return AjaxResult.error("删除失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	
	/**
	 *  注册新用户信息;  /user/adduser
	 */
	@RequestMapping("adduser")
	@ResponseBody
	public AjaxResult adduser(User user){
		try {
			userMagService.saveNewUser(user);
		} catch (Exception e) {
			return AjaxResult.error("注册失败!!!");
		}
		return AjaxResult.oK();
	}
	
	
	
}
