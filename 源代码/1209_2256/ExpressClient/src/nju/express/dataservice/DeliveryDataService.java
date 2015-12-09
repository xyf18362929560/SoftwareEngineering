package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Delivery;

public interface DeliveryDataService extends Remote {
	Vector<Delivery> getAll() throws RemoteException;

	Vector<Delivery> getBySql(String sql, Object[] obs) throws RemoteException;

	Delivery getById(int id) throws RemoteException;

	int insert(Delivery delivery) throws RemoteException;

	boolean update(Delivery delivery) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
