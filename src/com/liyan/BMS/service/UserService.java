package liyan.BMS.service;

import liyan.BMS.dao.impl.UserDaoImpl;
import liyan.BMS.entity.User;

public class UserService {
	

	private static UserDaoImpl userDao = new UserDaoImpl();

	
	public static User login(User user) {
		return userDao.checkUser(user);
	}
	
	public static int registerUser(User user) {
		if(userDao.checkUserById(user.getId())!=null) {
			return 1;//此用户已存在
		}else {
			boolean res=userDao.saveUser(user);
			if(res) {
				return 2;//注册成功
			}else {
				return 3;//注册失败
			}
		}
	}
/*
检测登录时用户id存在
 */
	public static boolean isUserExist(String userId){
		if(userDao.checkUserById(userId)!=null){
			return true; //用户id存在
		}else{
			return false;
		}
	}
	
/*	
	//用户登录，返回登录用户的信息（对象）
	public static User login(User user) {
		return user;
	
	}
	//注册用户
	public static int registerUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
