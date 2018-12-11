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
����¼ʱ�û�id����
 */
	public static boolean isUserExist(String userId){
		if(userDao.checkUserById(userId)!=null){
			return true; //�û�id����
		}else{
			return false;
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
