package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.AccountDataService;
import nju.express.po.Account;
import nju.express.util.DAO;

public class AccountDataServiceImpl extends UnicastRemoteObject implements AccountDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1911333743760418581L;

	public AccountDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Account> getAll() throws RemoteException {
		return DAO.getList(Account.class);
	}

	@Override
	public Vector<Account> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Account.class, sql, obs);
	}

	@Override
	public Account getById(int id) throws RemoteException {
		return (Account) DAO.getObById(Account.class, id);

	}

	@Override
	public int insert(Account account) throws RemoteException {
		return DAO.insertGetGeneratedKey(account);
	}

	@Override
	public boolean update(Account account) throws RemoteException {
		return DAO.update(account, account.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Account.class, id);
	}

}
