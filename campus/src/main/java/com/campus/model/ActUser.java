package com.campus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动和用户联合主键表
	act_id2	int(11)
	user_id2	int(11)
	user_applyjoin	int(4)
	user_endjoin	int(4)
	act_innovatescore	int(4)
	act_executescore	int(4)
	act_processscore	int(4)
	act_finallyscore	int(4)
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
	private Integer  user_applyjoin;//用户预参与
	private Integer  user_endjoin;//用户实际参与
	private Integer  act_innovatescore;//活动创意得分
	private Integer  act_executescore;//活动执行得分
	private Integer  act_processscore;//活动流程得分
	private Integer  act_finallyscore;//活动诸侯得分
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
	public Integer getUser_applyjoin() {
		return user_applyjoin;
	}
	public void setUser_applyjoin(Integer user_applyjoin) {
		this.user_applyjoin = user_applyjoin;
	}
	public Integer getUser_endjoin() {
		return user_endjoin;
	}
	public void setUser_endjoin(Integer user_endjoin) {
		this.user_endjoin = user_endjoin;
	}
	public Integer getAct_innovatescore() {
		return act_innovatescore;
	}
	public void setAct_innovatescore(Integer act_innovatescore) {
		this.act_innovatescore = act_innovatescore;
	}
	public Integer getAct_executescore() {
		return act_executescore;
	}
	public void setAct_executescore(Integer act_executescore) {
		this.act_executescore = act_executescore;
	}
	public Integer getAct_processscore() {
		return act_processscore;
	}
	public void setAct_processscore(Integer act_processscore) {
		this.act_processscore = act_processscore;
	}
	public Integer getAct_finallyscore() {
		return act_finallyscore;
	}
	public void setAct_finallyscore(Integer act_finallyscore) {
		this.act_finallyscore = act_finallyscore;
	}
	//
	@Override
	public String toString() {
		return "ActUSer [actId2=" + actId2 + ", userId2=" + userId2 + ", user_applyjoin=" + user_applyjoin
				+ ", user_endjoin=" + user_endjoin + ", act_innovatescore=" + act_innovatescore + ", act_executescore="
				+ act_executescore + ", act_processscore=" + act_processscore + ", act_finallyscore=" + act_finallyscore
				+ "]";
	}

}
