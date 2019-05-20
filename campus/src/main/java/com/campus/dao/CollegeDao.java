package com.campus.dao;

import java.util.List;

import com.campus.model.College;

public interface CollegeDao {
	/**查询所有的学院信息*/
	public List<College> showAllCollege();
	/**插入新的学院信息*/
	public void saveNewCollegeInfo(String collegeName);
	/***/
	
	
}
