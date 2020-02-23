package com.sz.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sz.dao.UserDao;
import com.sz.entiy.Student;
import com.sz.util.SxtJdbc;

public class UserDaoImpl implements UserDao {

	@Override
	public Student userloginDao(String name, String pwd) {

		System.out.println("执行UserloginDao");
		// 声明对象
		Student student = null;
		Connection connection = null;
		PreparedStatement ps = null;// SQL命令发射器
		ResultSet resultSet = null;
		try {
			connection = SxtJdbc.getConnection();
			// 创建sql命令s
			String sql = "select * from t_student where name=? and pwd=?";
			// 创建sql命令发射器
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			resultSet = ps.executeQuery();
			// 遍历resultSet用While
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name2 = resultSet.getString("name");
				String pwd2 = resultSet.getString("pwd");
				int age = resultSet.getInt("age");
				int sex = resultSet.getInt("sex");
				Date enterDate = resultSet.getDate("enterdate");
				String clazz = resultSet.getString("clazz");
				// 存到一个对象中
				student = new Student(id, name2, pwd2, age, sex, enterDate,
						clazz);
			} else {
				System.out.println("UserDaoImpi.userlogin.Dao(查询不到数据)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	//@SuppressWarnings("finally")
	@Override
	public int selectcountDao() {
		Connection connection = null;
		PreparedStatement ps = null;// SQL命令 发送器
		ResultSet resultSet = null;
		try {
			// 创建连接
			connection = SxtJdbc.getConnection();
			// 写SQL语句
			String sql = "select count(*) from t_student";
			ps = connection.prepareStatement(sql);
			// 发送sql获取返回的结果
			// c+1获取返回的结果
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				System.out.println("count" + count);
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 

	}

	@Override
	public List selectAllDao(int start, int size) {
		// 声明对象
		ArrayList list = new ArrayList();// 大的集合
		Connection connection = null;
		PreparedStatement ps = null;// SQL命令发送器
		ResultSet resultSet = null;
		try {
			connection = SxtJdbc.getConnection();
			String sql = "select t.*,c.id cid,c.name cname from t_student t left join t_class c on t.clazz=c.id limit ?,?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, size);
			// 发送sql获取结果
			// c+1获取返回的结果
			resultSet = ps.executeQuery();
			// 遍历resultSet用While
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name2 = resultSet.getString("name");
				String pwd2 = resultSet.getString("pwd");
				int age = resultSet.getInt("age");
				int sex = resultSet.getInt("sex");
				Date enterDate = resultSet.getDate("enterdate");
				String clazz = resultSet.getString("clazz");
				String cid = resultSet.getString("cid");
				String cname = resultSet.getString("cname");
				ArrayList listonly = new ArrayList();
				listonly.add(id);
				listonly.add(name2);
				listonly.add(pwd2);
				listonly.add(age);
				listonly.add(sex);
				listonly.add(enterDate);
				listonly.add(clazz);
				listonly.add(cid);
				listonly.add(cname);
				// 集合嵌套
				list.add(listonly);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SxtJdbc.colseAll(resultSet, ps, connection);
		}
		return list;
	}

	@Override
	public int deleteStudentDao(String sid) {
		String sql = "delete from t_student where id=?";
		Object[] obs = { sid };
		return SxtJdbc.dmlUser(sql, obs);
	}

	@Override
	public int deleteStudentsDao(String sids) {
		StringBuilder sql = new StringBuilder(
				"delete from t_student where id in(");
		String[] str = sids.split(",");
		for (int i = 0; i < str.length; i++) {
			//是最后一个值
			if(i==str.length-1){
				sql.append("?)");
				//否者继续加,
			}else{ 
				sql.append("?,");
			}
			System.out.println("UserDaoImpl.deleteStudentsDao():"+sql);
		}
		return SxtJdbc.dmlUser(sql, str);
	}

}
