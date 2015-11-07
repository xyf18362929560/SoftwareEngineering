package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Collection;

public interface CollectionDataService extends Remote {
	Vector<Collection> getAll() throws RemoteException;

	Collection getById(int id) throws RemoteException;

	int insert(Collection collection) throws RemoteException;

	int update(Collection collection) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
