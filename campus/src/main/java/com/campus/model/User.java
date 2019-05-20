package com.campus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**   用户实体类
	user_id	int(11)
	user_name	varchar(20)
	user_password	varchar(20)
	user_nickname	varchar(20)
	user_sex	varchar(2)
	user_age	int(4)
	user_phone	varchar(11)
	user_college	varchar(20)
	user_score	int(11) unsigned
	user_level	int(1) unsigned   0:普通用户; 1-管理员;  2-审核员;  3-超级管理员
	user_apply	int(1)
	user_attend	int(1)
	user_description	varchar(255)
	user_applystatus	int(1)    活动申请状态(0-未申请,1-申请中,2-审核通过)
	user_foundtime	date
 * @author Administrator
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	//数据库字段
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;//用户id,主键,自增长
	
	private String userName;//用户账号
	private String userPassword;//用户密码
	private String userNickname;//用户昵称
	private String userSex;//用户性别
	private Integer userAge;//用户年龄
	private String userCollege;//所属学院
	private Integer userScore;//信用得分
	private Integer userLevel;//用户等级; 0:普通用户; 1-管理员;  2-审核员;  3-超级管理员
	private String userPhone;//联系电话
	private Integer userApply;//预报名;  0-无,1-有
	private Integer userAttend;//参加活动; 0-无,1-有
	private String userDescription;//用户描述
	private Integer userApplystatus;//申请状态
	private Date userFoundtime;//用户注册时间
	//get和set方的设置
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserCollege() {
		return userCollege;
	}
	public void setUserCollege(String userCollege) {
		this.userCollege = userCollege;
	}
	public Integer getUserScore() {
		return userScore;
	}
	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getUserApply() {
		return userApply;
	}
	public void setUserApply(Integer userApply) {
		this.userApply = userApply;
	}
	public Integer getUserAttend() {
		return userAttend;
	}
	public void setUserAttend(Integer userAttend) {
		this.userAttend = userAttend;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getUserApplystatus() {
		return userApplystatus;
	}
	public void setUserApplystatus(Integer userApplystatus) {
		this.userApplystatus = userApplystatus;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getUserFoundtime() {
		return userFoundtime;
	}
	public void setUserFoundtime(Date userFoundtime) {
		this.userFoundtime = userFoundtime;
	}
	//
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userNickname=" + userNickname + ", userSex=" + userSex + ", userAge=" + userAge + ", userCollege="
				+ userCollege + ", userScore=" + userScore + ", userLevel=" + userLevel + ", userPhone=" + userPhone
				+ ", userApply=" + userApply + ", userAttend=" + userAttend + ", userDescription=" + userDescription
				+ ", userApplystatus=" + userApplystatus + ", userFoundtime=" + userFoundtime + "]";
	}
	
	
}
