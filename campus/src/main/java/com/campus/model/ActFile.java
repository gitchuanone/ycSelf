package com.campus.model;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * 活动文件存储表
	id	int(11)
	act_id	int(11)
	apply_file1	varchar(255)
	apply_file2	varchar(255)
	apply_file3	varchar(255)
	reply_file1	varchar(255)
	reply_file2	varchar(255)
	apply-file_name
	reply_file_name
 *
 */
@Table(name="act_file")
public class ActFile implements Serializable{
	private static final long serialVersionUID = 1L;
	//
	private Integer id;//主键,自增长
	private Integer actId;//活动id值
	private String applyFile1;//申请文件1地址
	private String applyFile2;//申请文件2地址
	private String applyFile3;//申请文件3地址
	private String replyFile1;//回复文件1地址
	private String replyFile2;//回复文件地址
	
	private String applyFileName;
	private String replyFileName;
	//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public String getApplyFile1() {
		return applyFile1;
	}
	public void setApplyFile1(String applyFile1) {
		this.applyFile1 = applyFile1;
	}
	public String getApplyFile2() {
		return applyFile2;
	}
	public void setApplyFile2(String applyFile2) {
		this.applyFile2 = applyFile2;
	}
	public String getApplyFile3() {
		return applyFile3;
	}
	public void setApplyFile3(String applyFile3) {
		this.applyFile3 = applyFile3;
	}
	public String getReplyFile1() {
		return replyFile1;
	}
	public void setReplyFile1(String replyFile1) {
		this.replyFile1 = replyFile1;
	}
	public String getReplyFile2() {
		return replyFile2;
	}
	public void setReplyFile2(String replyFile2) {
		this.replyFile2 = replyFile2;
	}
	public String getApplyFileName() {
		return applyFileName;
	}
	public void setApplyFileName(String applyFileName) {
		this.applyFileName = applyFileName;
	}
	public String getReplyFileName() {
		return replyFileName;
	}
	public void setReplyFileName(String replyFileName) {
		this.replyFileName = replyFileName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//
	@Override
	public String toString() {
		return "ActFile [id=" + id + ", actId=" + actId + ", applyFile1=" + applyFile1 + ", applyFile2=" + applyFile2
				+ ", applyFile3=" + applyFile3 + ", replyFile1=" + replyFile1 + ", replyFile2=" + replyFile2
				+ ", applyFileName=" + applyFileName + ", replyFileName=" + replyFileName + "]";
	}
	
	
	
	
}
