package liyan.BMS.dao;

import liyan.BMS.entity.User;

public interface UserDao {
	public boolean saveUser(User user);//添加用户
	public boolean delUser(int id);//删除用户
	public boolean updateUser(User user);//更新用户
	public User checkUser(User user);//查询用户

	public User checkUserById(String userId);//根据用户Id查询用户
}
