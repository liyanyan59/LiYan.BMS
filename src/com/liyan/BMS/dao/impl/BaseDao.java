package com.liyan.BMS.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;



public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/?useSSL=false";
    //硬编码的方式把它写死
	//也可放入xml文件中解析
	
	/*
	 * 与数据库建立连接
	 * 返回数据库连接对象
	 * */
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(URL,"root","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}//通过URL获得数据库的连接
		
		return conn;//返回连接对象
	}
	
	/*
	 * 释放相应的资源
	 */
	public void closeAll(ResultSet rs,java.sql.PreparedStatement pstmt,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 这个方法可以完成增删改所有的操作
	 * 返回bool值
	 */
	public boolean operateUpdate(String sql,List<Object> params) {
		int res = 0;//影响的行数
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConn();//建立数据库连接
			pstmt=(PreparedStatement) conn.prepareStatement(sql);//装载sql连接
			if(params!=null) {
				//假如有？，则在执行前把?占位符替换掉
				for(int i=0;i<params.size();i++) {
					pstmt.setObject(i+1, params.get(i));
				}
			}
			res=pstmt.executeUpdate();//
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pstmt, conn);
		}//把资源释放掉
		return res>0?true:false;
	}
	
	//使用泛型方法和反射机制进行封装
	public <T> List<T> operateQuery(String sql,List<Object> params,Class<T> cls) throws Exception{
		Connection conn=null;
		java.sql.PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<T> data=new ArrayList<T>();
		try {
			conn=getConn();//建立数据库连接
			pstmt=conn.prepareStatement(sql);//装载sql连接
			if(params!=null) {
				//假如有？，则在执行前把?占位符替换掉
				for(int i=0;i<params.size();i++) {
					
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs=pstmt.executeQuery();//把查询出来的记录封装成对应的实体类对象
			ResultSetMetaData rsd = rs.getMetaData();//得到记录集元数据对象
			//通过值对象可以得到表的结构，包括列名，列的个数，列的数据类型
			
			while(rs.next()) {
				T m=cls.newInstance();
				//getColumnCount获得列的个数
				for(int i=0;i<rsd.getColumnCount();i++) {
					String col_name=rsd.getColumnName(i+1);//获得列名
					Object value=rs.getObject(col_name);//获得列里面的值
					Field field=cls.getDeclaredField(col_name);//获得m的属性
					field.setAccessible(true);//给私有属性设置可访问权
					field.set(m, value);//给对象的私有属性赋值
				}
				data.add(m);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pstmt, conn);
		}//把资源释放掉
		return data;
	}
	
	
	
	
	
	
	
}
