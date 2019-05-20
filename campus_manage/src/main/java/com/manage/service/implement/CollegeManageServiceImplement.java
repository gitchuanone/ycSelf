package com.manage.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.CollegeDao;
import com.manage.model.College;
import com.manage.service.CollegeManageService;
@Service
public class CollegeManageServiceImplement implements CollegeManageService {
	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public List<College> showAllCollege() {
		List<College> list=collegeDao.showAllCollege();
		return list;
	}

	@Override
	public void removeCollegeByCollegeId(Integer collegeId) {
		collegeDao.removeCollegeByCollegeId(collegeId);
	}

	@Override
	public void addNewCollege(String collegeName) {
		collegeDao.addNewCollege(collegeName);
		
	}

}
