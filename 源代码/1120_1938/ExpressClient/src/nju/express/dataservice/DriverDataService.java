package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Driver;

public interface DriverDataService extends Remote {
	Vector<Driver> getAll() throws RemoteException;

	Driver getById(int id) throws RemoteException;

	int insert(Driver driver) throws RemoteException;

	boolean update(Driver driver) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
