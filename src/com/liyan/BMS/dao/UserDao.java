package com.liyan.BMS.dao;

import com.liyan.BMS.entity.User;

public interface UserDao {
	public boolean saveUser(User user);//����û�
	public boolean delUser(int id);//ɾ���û�
	public boolean updateUser(User user);//�����û�
	public User checkUser(User user);//��ѯ�û�
	
}
