package com.sz.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sz.dao.UserDao;
import com.sz.dao.impl.UserDaoImpl;
import com.sz.entiy.Student;
import com.sz.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public Student userlogin(String name, String pwd) {
		UserDao userDao=new UserDaoImpl();
		Student student=userDao.userloginDao(name,pwd);
		return student;
	}
	@Override
	public List selectAllService(int start,int size) {
		//调用DAO类
		UserDao userDao=new UserDaoImpl();
		return userDao.selectAllDao(start, size);
	}
	@Override
	public int selectcountService() {
		return new UserDaoImpl().selectcountDao();
	}
	@Override
	public int deleteStudentService(String sid) {
		
		return new UserDaoImpl().deleteStudentDao(sid);
	}
	@Override
	public int deleteStudentsService(String sids) {
		// TODO Auto-generated method stub
		return new UserDaoImpl().deleteStudentsDao(sids);
	}
	
} 
