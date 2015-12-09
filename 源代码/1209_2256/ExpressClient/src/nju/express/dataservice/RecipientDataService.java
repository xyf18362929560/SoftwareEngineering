package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Recipient;

public interface RecipientDataService extends Remote {
	Vector<Recipient> getAll() throws RemoteException;

	Vector<Recipient> getBySql(String sql, Object[] obs) throws RemoteException;

	Recipient getById(int id) throws RemoteException;

	int insert(Recipient recipient) throws RemoteException;

	boolean update(Recipient recipientS) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
