package liyan.BMS.dao;

import liyan.BMS.entity.User;

public interface UserDao {
	public boolean saveUser(User user);//����û�
	public boolean delUser(int id);//ɾ���û�
	public boolean updateUser(User user);//�����û�
	public User checkUser(User user);//��ѯ�û�

	public User checkUserById(String userId);//�����û�Id��ѯ�û�
}
