package com.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.model.College;

public interface CollegeDao {
	/**显示所有学院信息*/
	List<College> showAllCollege();
	/**根据学院id删除学院*/
	void removeCollegeByCollegeId(@Param("collegeId") Integer collegeId);
	/**新增学院信息*/
	void addNewCollege(String collegeName);
	
}
