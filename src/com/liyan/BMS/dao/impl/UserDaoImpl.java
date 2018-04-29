package com.liyan.BMS.dao.impl;
import java.util.ArrayList;
import java.util.List;

import com.liyan.BMS.dao.UserDao;
import com.liyan.BMS.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public boolean saveUser(User user) {
		String sql="INSERT INTO system_db.user (userId, userType) VALUES (?,?)";
		List<Object>params=new ArrayList<Object>();
		params.add(user.getId());
		params.add(user.getType());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean delUser(int id) {
		String sql="delete from user where id=?";
		List<Object>params=new ArrayList<Object>();
		params.add(id);
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateUser(User user) {
		String sql="update user set userId=?,userType=? where id=?";
		List<Object>params=new ArrayList<Object>();
		params.add(user.getId());
		params.add(user.getType());
		return this.operateUpdate(sql, params);
	}

	@Override
	public User checkUser(User user) {
		List<User> uList=null;
		String sql="SELECT userId,userType FROM system_db.user where userId=? and userType=?";
		List<Object>params=new ArrayList<Object>();
		params.add(user.getId());
		params.add(user.getType());
		try {
			uList=this.operateQuery(sql, params, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(uList.size()>0) {
			return uList.get(0);
		}
		return null;
	}

}
