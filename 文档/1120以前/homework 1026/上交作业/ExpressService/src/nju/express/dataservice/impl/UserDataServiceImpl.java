package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class UserDataServiceImpl extends UnicastRemoteObject implements UserDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public UserDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public User getUser(String name, String password) throws RemoteException {
		String sql = "select *from user where user_name = ? and user_password =? ";
		Object[] obs = { name, password };
		ArrayList<User> ar = BaseDataServiceImpl.getListBySql(User.class, sql, obs);
		return (ar.size() == 1) ? ar.get(0) : null;
		// User user=new User();
		// user.setId(1);
		// user.setUser_name("xyf");
		// user.setUser_password("123");
		// return user;
	}

	@Override
	public User getUser(int id) throws RemoteException {

		return (User) BaseDataServiceImpl.getObById(User.class, 1);
	}

}
