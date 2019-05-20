package com.campus.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.dao.CollegeDao;
import com.campus.model.College;
import com.campus.service.CollegeService;
@Service
public class CollegeServiceImplement implements CollegeService {
	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public List<College> showAllCollege() {
		List<College> collegeList=collegeDao.showAllCollege();
		return collegeList;
	}

	@Override
	public void saveNewCollegeInfo(String collegeName) {
		collegeDao.saveNewCollegeInfo(collegeName);
	}

}
