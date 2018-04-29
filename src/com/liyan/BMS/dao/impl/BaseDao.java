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
    //Ӳ����ķ�ʽ����д��
	//Ҳ�ɷ���xml�ļ��н���
	
	/*
	 * �����ݿ⽨������
	 * �������ݿ����Ӷ���
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
		}//ͨ��URL������ݿ������
		
		return conn;//�������Ӷ���
	}
	
	/*
	 * �ͷ���Ӧ����Դ
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
	 * ����������������ɾ�����еĲ���
	 * ����boolֵ
	 */
	public boolean operateUpdate(String sql,List<Object> params) {
		int res = 0;//Ӱ�������
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConn();//�������ݿ�����
			pstmt=(PreparedStatement) conn.prepareStatement(sql);//װ��sql����
			if(params!=null) {
				//�����У�������ִ��ǰ��?ռλ���滻��
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
		}//����Դ�ͷŵ�
		return res>0?true:false;
	}
	
	//ʹ�÷��ͷ����ͷ�����ƽ��з�װ
	public <T> List<T> operateQuery(String sql,List<Object> params,Class<T> cls) throws Exception{
		Connection conn=null;
		java.sql.PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<T> data=new ArrayList<T>();
		try {
			conn=getConn();//�������ݿ�����
			pstmt=conn.prepareStatement(sql);//װ��sql����
			if(params!=null) {
				//�����У�������ִ��ǰ��?ռλ���滻��
				for(int i=0;i<params.size();i++) {
					
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs=pstmt.executeQuery();//�Ѳ�ѯ�����ļ�¼��װ�ɶ�Ӧ��ʵ�������
			ResultSetMetaData rsd = rs.getMetaData();//�õ���¼��Ԫ���ݶ���
			//ͨ��ֵ������Եõ���Ľṹ�������������еĸ������е���������
			
			while(rs.next()) {
				T m=cls.newInstance();
				//getColumnCount����еĸ���
				for(int i=0;i<rsd.getColumnCount();i++) {
					String col_name=rsd.getColumnName(i+1);//�������
					Object value=rs.getObject(col_name);//����������ֵ
					Field field=cls.getDeclaredField(col_name);//���m������
					field.setAccessible(true);//��˽���������ÿɷ���Ȩ
					field.set(m, value);//�������˽�����Ը�ֵ
				}
				data.add(m);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			closeAll(rs, pstmt, conn);
		}//����Դ�ͷŵ�
		return data;
	}
	
	
	
	
	
	
	
}
