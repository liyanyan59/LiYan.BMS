package com.liyan.BMS.service;

import com.liyan.BMS.dao.impl.UserDaoImpl;
import com.liyan.BMS.entity.User;

public class UserService {
	

	private static UserDaoImpl userDao = new UserDaoImpl();
	/*public UserService() {
		
		userDao = new UserDaoImpl();
	}*/
	
	
	public static User login(User user) {
		return userDao.checkUser(user);
	}
	
	public static int registerUser(User user) {
		if(userDao.checkUser(user)!=null) {
			return 1;//���û��Ѵ���
		}else {
			boolean res=userDao.saveUser(user);
			if(res) {
				return 2;//ע��ɹ�
			}else {
				return 3;//ע��ʧ��
			}
		}
	}
	
/*	
	//�û���¼�����ص�¼�û�����Ϣ������
	public static User login(User user) {
		return user;
	
	}
	//ע���û�
	public static int registerUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
