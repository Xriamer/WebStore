package com.sz.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//import org.apache.log4j.Logger;

public class SxtJdbc {
	
	
	//private static Logger logger = Logger.getLogger(SxtJdbc.class.getName());
	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";	
	
	//静态代码块，加载类的时候会自动执行，就执行一遍
	static{
		//创建properties对象
		Properties properties = new Properties();
		try {
			properties.load(SxtJdbc.class.getClassLoader().getResourceAsStream("sxtJdbc.properties"));
			String database = properties.getProperty("database");
			driver = properties.getProperty(database+"driver");
			url = properties.getProperty(database+"url");
			user = properties.getProperty(database+"username");
			password = properties.getProperty(database+"password");
			//1：加载驱动
			//logger.info("加载驱动");
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//创建数据库连接
	public static Connection getConnection(){
		Connection connection = null;
		try {
			//logger.info("创建连接");
			//创建连接
			 connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("SxtJdbc.getConnection(找不到对应的连接,请检查参数信息)"+"url:"+url+"|"+"user:"+user+"|"+"password:"+password);
			//logger.error("创建连接失败");
			e.printStackTrace();
		}
		return connection;
	}
	
	//创建sql命令发送器Statement
	public static Statement getStatement(Connection connection){
		Statement statement =null; 
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	
	//创建prepareStatement命令发送器  CharSequence：是String,StringBuffer,StringBuilder的父接口
	public static PreparedStatement getPreparedStatement(Connection connection,CharSequence sql){
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	//关闭资源链接
	public static void colseAll(ResultSet resultSet,Statement statement,Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-gene rated catch block
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//创建DML（insert,update,delete）方法
	public static int dmlUser(CharSequence sql,Object...objects){
		//创建变量n
		int n = 0;
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			n = preparedStatement.executeUpdate();
			System.out.println("SxtJdbc.dmlUser()的n的值=="+n);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			colseAll(null, preparedStatement, connection);
		}
		return n;
	}
		
	
	
}
