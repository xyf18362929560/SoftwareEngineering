package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Account;

public interface AccountDataService extends Remote {
	Vector<Account> getAll() throws RemoteException;

	Vector<Account> getBySql(String sql, Object[] obs) throws RemoteException;

	Account getById(int id) throws RemoteException;

	int insert(Account account) throws RemoteException;

	boolean update(Account account) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
