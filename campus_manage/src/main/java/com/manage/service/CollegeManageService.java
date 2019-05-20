package com.manage.service;

import java.util.List;

import com.manage.model.College;

public interface CollegeManageService {
	/**显示所有学院信息*/
	List<College> showAllCollege();
	/**根据学院id删除学院*/
	void removeCollegeByCollegeId(Integer collegeId);
	/**新增学院信息*/
	void addNewCollege(String collegeName);

}
