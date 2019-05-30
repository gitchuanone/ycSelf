package com.campus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**活动实体类
	activity_id	int(11)
	activity_theme	varchar(255)
	activity_organizer	varchar(255)
	activity_place	varchar(255)
	activity_predtime	datetime
	activity_processtime	datetime
	activity_endtime	datetime
	activity_description	varchar(255)
	activity_predjoin	int(6)
	activity_realjoin	int(6)
	activity_innovatescore	double(2)
	activity_executescore	double(2)
	activity_processscore	double(2)
	activity_finallyscore	double(2)
	activity_applystatus	int(2)
	activity_orgcollege	varchar(255) 
	activity_username	varchar(20)
	apply_filename
	reply_filename
	activity_impltime
 * @author Administrator
 */
@Entity
@Table(name="activity")
public class Activity  implements Serializable{
	private static final long serialVersionUID = 1L;
	//
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer activityId;//活动id,主键,自增长
	
	private	String activityTheme;//活动主题
	private String activityOrganizer;//活动组织者(负责人)
	private	String activityPlace;//活动举办地点
	private Date activityPredtime;//活动预估计时间
	private Date activityProcesstime;//活动公告时间
	private Date activityEndtime;//活动正式进行时间
	private String activityDescription;//活动描述
	private Integer activityPredjoin;//预估计参与人数
	private Integer activityRealjoin;//实际参与人数
	
	private Double activityInnovatescore;//活动创意的分
	private Double activityExecutescore;//活动流程执行力得分
	private Double activityProcessscore;//活动流程(背景,装饰等)得分
	private Double activityFinallyscore;//活动最终得分
	private String activityOrgcollege;//活动举办学院
	private Integer activityApplystatus;//活动申请状态(0-未申请,1-申请中,2-审核拒绝,3-审核通过)
	private String activityUsername; //
	private String applyFilename;//活动申请文件名称
	private String replyFilename;//活动回复文件民称
	private Date activityImpltime;//活动正式进行时间
	//
	public Date getActivityImpltime() {
		return activityImpltime;
	}
	public void setActivityImpltime(Date activityImpltime) {
		this.activityImpltime = activityImpltime;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityTheme() {
		return activityTheme;
	}
	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}
	public String getActivityOrganizer() {
		return activityOrganizer;
	}
	public void setActivityOrganizer(String activityOrganizer) {
		this.activityOrganizer = activityOrganizer;
	}
	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}
	public Date getActivityPredtime() {
		return activityPredtime;
	}
	public void setActivityPredtime(Date activityPredtime) {
		this.activityPredtime = activityPredtime;
	}
	public Date getActivityProcesstime() {
		return activityProcesstime;
	}
	public void setActivityProcesstime(Date activityProcesstime) {
		this.activityProcesstime = activityProcesstime;
	}
	public Date getActivityEndtime() {
		return activityEndtime;
	}
	public void setActivityEndtime(Date activityEndtime) {
		this.activityEndtime = activityEndtime;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public Integer getActivityPredjoin() {
		return activityPredjoin;
	}
	public void setActivityPredjoin(Integer activityPredjoin) {
		this.activityPredjoin = activityPredjoin;
	}
	public Integer getActivityRealjoin() {
		return activityRealjoin;
	}
	public void setActivityRealjoin(Integer activityRealjoin) {
		this.activityRealjoin = activityRealjoin;
	}
	public Double getActivityInnovatescore() {
		return activityInnovatescore;
	}
	public void setActivityInnovatescore(Double activityInnovatescore) {
		this.activityInnovatescore = activityInnovatescore;
	}
	public Double getActivityExecutescore() {
		return activityExecutescore;
	}
	public void setActivityExecutescore(Double activityExecutescore) {
		this.activityExecutescore = activityExecutescore;
	}
	public Double getActivityProcessscore() {
		return activityProcessscore;
	}
	public void setActivityProcessscore(Double activityProcessscore) {
		this.activityProcessscore = activityProcessscore;
	}
	public Double getActivityFinallyscore() {
		return activityFinallyscore;
	}
	public void setActivityFinallyscore(Double activityFinallyscore) {
		this.activityFinallyscore = activityFinallyscore;
	}
	public String getActivityOrgcollege() {
		return activityOrgcollege;
	}
	public void setActivityOrgcollege(String activityOrgcollege) {
		this.activityOrgcollege = activityOrgcollege;
	}
	public Integer getActivityApplystatus() {
		return activityApplystatus;
	}
	public void setActivityApplystatus(Integer activityApplystatus) {
		this.activityApplystatus = activityApplystatus;
	}
	public String getActivityUsername() {
		return activityUsername;
	}
	public void setActivityUsername(String activityUsername) {
		this.activityUsername = activityUsername;
	}
	public String getApplyFilename() {
		return applyFilename;
	}
	public void setApplyFilename(String applyFilename) {
		this.applyFilename = applyFilename;
	}
	public String getReplyFilename() {
		return replyFilename;
	}
	public void setReplyFilename(String replyFilename) {
		this.replyFilename = replyFilename;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityTheme=" + activityTheme + ", activityOrganizer="
				+ activityOrganizer + ", activityPlace=" + activityPlace + ", activityPredtime=" + activityPredtime
				+ ", activityProcesstime=" + activityProcesstime + ", activityEndtime=" + activityEndtime
				+ ", activityDescription=" + activityDescription + ", activityPredjoin=" + activityPredjoin
				+ ", activityRealjoin=" + activityRealjoin + ", activityInnovatescore=" + activityInnovatescore
				+ ", activityExecutescore=" + activityExecutescore + ", activityProcessscore=" + activityProcessscore
				+ ", activityFinallyscore=" + activityFinallyscore + ", activityOrgcollege=" + activityOrgcollege
				+ ", activityApplystatus=" + activityApplystatus + ", activityUsername=" + activityUsername
				+ ", applyFilename=" + applyFilename + ", replyFilename=" + replyFilename + ", activityImpltime="
				+ activityImpltime + "]";
	}
	
	
}
