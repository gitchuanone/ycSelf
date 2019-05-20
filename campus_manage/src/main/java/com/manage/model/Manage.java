package com.manage.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员的实体类
 * @author Administrator
 */
@Entity
@Table(name="manage")
public class Manage implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	mag_id	int(11)
	mag_name	varchar(255)
	mag_password	varchar(255)
	mag_nickname	varchar(255)
	mag_email	varchar(255)
	mag_phone	varchar(255)
	mag_description	varchar(255)
	mag_foundtime	datetime
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer magId;//主键,自增长
	
	private String magName;//账号
	private String magPassword;//密码
	private String magNickname;//昵称
	private String magEmail;//邮箱
	private String magPhone;//电话
	private String magDescription;//描述
	private Date magFoundtime;//创建时间
	//get and set
	public Integer getMagId() {
		return magId;
	}
	public void setMagId(Integer magId) {
		this.magId = magId;
	}
	public String getMagName() {
		return magName;
	}
	public void setMagName(String magName) {
		this.magName = magName;
	}
	public String getMagPassword() {
		return magPassword;
	}
	public void setMagPassword(String magPassword) {
		this.magPassword = magPassword;
	}
	public String getMagNickname() {
		return magNickname;
	}
	public void setMagNickname(String magNickname) {
		this.magNickname = magNickname;
	}
	public String getMagEmail() {
		return magEmail;
	}
	public void setMagEmail(String magEmail) {
		this.magEmail = magEmail;
	}
	public String getMagPhone() {
		return magPhone;
	}
	public void setMagPhone(String magPhone) {
		this.magPhone = magPhone;
	}
	public String getMagDescription() {
		return magDescription;
	}
	public void setMagDescription(String magDescription) {
		this.magDescription = magDescription;
	}
	public Date getMagFoundtime() {
		return magFoundtime;
	}
	public void setMagFoundtime(Date magFoundtime) {
		this.magFoundtime = magFoundtime;
	}
	//
	@Override
	public String toString() {
		return "Manage [magId=" + magId + ", magName=" + magName + ", magPassword=" + magPassword + ", magNickname="
				+ magNickname + ", magEmail=" + magEmail + ", magPhone=" + magPhone + ", magDescription="
				+ magDescription + ", magFoundtime=" + magFoundtime + "]";
	}
	
}
