package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.UserDataService;
import nju.express.po.User;
import nju.express.util.DAO;

public class UserDataServiceImpl extends UnicastRemoteObject implements UserDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2225214643093609104L;

	public UserDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public User getUser(String name, String password) throws RemoteException {
		String sql = "select * from user where user_name = ? and user_password =? ";
		Object[] obs = { name, password };
		Vector<User> ar = DAO.getListBySql(User.class, sql, obs);

		return (ar.size() == 1) ? ar.get(0) : null;

	}

	@Override
	public User getById(int id) throws RemoteException {

		return (User) DAO.getObById(User.class, id);
	}

	@Override
	public Vector<User> getAll() throws RemoteException {
		return DAO.getList(User.class);
	}

	@Override
	public int insert(User user) throws RemoteException {
		return DAO.insertGetGeneratedKey(user);
	}

	@Override
	public boolean update(User user) throws RemoteException {

		return DAO.update(user, user.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(User.class, id);

	}

	@Override
	public Vector<User> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(User.class, sql, obs);
	}

	

}
