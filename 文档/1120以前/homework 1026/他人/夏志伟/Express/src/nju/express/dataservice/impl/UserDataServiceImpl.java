package nju.express.dataservice.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class UserDataServiceImpl extends BaseDataServiceImpl implements UserDataService{

	@Override
	public User getUser(String name, String password) {
		String sql="select *from user where user_name = ? and user_password =? ";
		Object[]obs={name,password};
		ArrayList<User> ar=getListBySql(User.class, sql, obs);
		return (ar.size() == 1) ? ar.get(0) : null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(int id, User user) {
		// TODO Auto-generated method stub
		
	}

}
