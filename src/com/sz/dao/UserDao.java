package com.sz.dao;

import java.util.List;

import com.sz.entiy.Student;

public interface UserDao {
	//登录
	Student userloginDao(String name,String pwd);
	//查询所有学生的信息
	List selectAllDao(int start,int size);
	int selectcountDao();
	int deleteStudentDao(String sid);
	int deleteStudentsDao(String sids);
		
	
}
