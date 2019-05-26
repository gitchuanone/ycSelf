package com.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.AjaxResult;
import com.campus.common.FileUtil;
import com.campus.model.ActFile;
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
	//获得Springboot提供的mongodb的GridFS对象
	@Autowired
	private GridFsTemplate  gridFsTemplate ;
	
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
	
	
	/***
	 *  活动申请上传文件;  并修改活动申请状态       /activity-apply/uploadApplyActivityFile
	 *  //添加文件mongodb中id
		list.add(mongoId);
		//添加文件名
		list.add(fileName);
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="uploadApplyActivityFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult  uploadApplyActivityFile(HttpServletRequest req) {
		try {
			//从request中获取前端传送的formdata数据
			String uploadFileName = req.getParameter("uploadFileName");
			Integer activityId = Integer.parseInt(req.getParameter("activityId"));
			//判断act_file中是否有文件存储数据
			ActFile oldactFile=actService.isFileExitByActiviyId(activityId);
			if(oldactFile!=null) {
				//有文件记录,先删除文件
				String deleteId=oldactFile.getApplyFile1();
				FileUtil.deleteMongoFileByObjectId(deleteId, gridFsTemplate);
			}
			//接受上传文件,活动id;获取存入mongodb返回的文件信息
			ActFile actfile=FileUtil.uploadFile(req,uploadFileName,gridFsTemplate);
			actfile.setActId(activityId);
			//判断是否为第一次存入文件
			if(oldactFile==null) {
				//没有数据,新插入数据记录
				actService.saveNewUploadFileInfo(actfile);
			}
			//已经有上传文件记录,则新上传文件记录覆盖之前记录
			actService.modifyActivityfileInfoByActivityId(actfile);
			//改变申请状态
			actService.uploadApplyActivityApplystatusByActivityId(activityId);
			
			//将文件名称存入activity表中
			Activity activity=new Activity();
			activity.setActivityId(activityId);
			ActFile newActFile=actService.isFileExitByActiviyId(activityId);
			activity.setApplyFilename(newActFile.getApplyFileName());
			actService.modifyApplyFilenameWhenUploadFile(activity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("上传文件失败!");
		}
		return AjaxResult.oK();
	}
	
	
}
