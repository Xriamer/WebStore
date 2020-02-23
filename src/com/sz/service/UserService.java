package com.sz.service;

import java.util.List;

import com.sz.entiy.Student;

public interface UserService {
	Student userlogin(String name, String pwd);

	List selectAllService(int start, int size); 
	int selectcountService();
	int deleteStudentService(String sid);
	int deleteStudentsService(String sids);
}
