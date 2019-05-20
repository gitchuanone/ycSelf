package com.campus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学院实体类
  	college_id	int(11)
	college_name	varchar(20)
	college_state	int(2)
 */
@Entity
@Table(name="college")
public class College  implements Serializable{
	private static final long serialVersionUID = 1L;
	//
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer collegeId;//学院id,主键,自增长
	
	private String collegeName;//学院名称
	private int collegeState;//学院状态值;  0-消失,1-存在
	//set  and get
	public Integer getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public int getCollegeState() {
		return collegeState;
	}
	public void setCollegeState(int collegeState) {
		this.collegeState = collegeState;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + ", collegeState=" + collegeState
				+ "]";
	}
	
	
	
}
