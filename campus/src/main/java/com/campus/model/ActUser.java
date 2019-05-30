package com.campus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动和用户联合主键表
	act_id2	int(11)
	user_id2	int(11)
	user_applyjoin	int(2)
	user_endjoin	int(2)
	user_exitjoin	int(2)
	act_innovatescore	double(2)
	act_executescore	double(2)
	act_processscore	double(2)
	act_finallyscore	double(2)
 * @author Administrator
 *
 */
//@Entity//加了卡死,服务器拒绝连接
@Table(name="act_user")
public class ActUser implements Serializable{
	private static final long serialVersionUID = 1L;
	//
	@Column(name="act_id2")
	private Integer actId2;//活动主键
	@Column(name="user_id2")
	private Integer userId2;//用户主键
	private Integer  userApplyjoin;//用户预参与
	private Integer  userEndjoin;//用户实际参与
	private Integer  userExitjoin;//用户退出登记
	
	private Double  actInnovatescore;//活动创意得分
	private Double  actExecutescore;//活动执行得分
	private Double  actProcessscore;//活动流程得分
	private Double  actFinallyscore;//活动诸侯得分
	//set and get
	public Integer getActId2() {
		return actId2;
	}
	public void setActId2(Integer actId2) {
		this.actId2 = actId2;
	}
	public Integer getUserId2() {
		return userId2;
	}
	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	}
	public Integer getUserApplyjoin() {
		return userApplyjoin;
	}
	public void setUserApplyjoin(Integer userApplyjoin) {
		this.userApplyjoin = userApplyjoin;
	}
	public Integer getUserEndjoin() {
		return userEndjoin;
	}
	public void setUserEndjoin(Integer userEndjoin) {
		this.userEndjoin = userEndjoin;
	}
	public Integer getUserExitjoin() {
		return userExitjoin;
	}
	public void setUserExitjoin(Integer userExitjoin) {
		this.userExitjoin = userExitjoin;
	}
	public Double getActInnovatescore() {
		return actInnovatescore;
	}
	public void setActInnovatescore(Double actInnovatescore) {
		this.actInnovatescore = actInnovatescore;
	}
	public Double getActExecutescore() {
		return actExecutescore;
	}
	public void setActExecutescore(Double actExecutescore) {
		this.actExecutescore = actExecutescore;
	}
	public Double getActProcessscore() {
		return actProcessscore;
	}
	public void setActProcessscore(Double actProcessscore) {
		this.actProcessscore = actProcessscore;
	}
	public Double getActFinallyscore() {
		return actFinallyscore;
	}
	public void setActFinallyscore(Double actFinallyscore) {
		this.actFinallyscore = actFinallyscore;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	@Override
	public String toString() {
		return "ActUser [actId2=" + actId2 + ", userId2=" + userId2 + ", userApplyjoin=" + userApplyjoin
				+ ", userEndjoin=" + userEndjoin + ", userExitjoin=" + userExitjoin + ", actInnovatescore="
				+ actInnovatescore + ", actExecutescore=" + actExecutescore + ", actProcessscore=" + actProcessscore
				+ ", actFinallyscore=" + actFinallyscore + "]";
	}
	
}
