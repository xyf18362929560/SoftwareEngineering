package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Fare;

public interface FareDataService extends Remote {
	Vector<Fare> getAll() throws RemoteException;

	Vector<Fare> getBySql(String sql, Object[] obs) throws RemoteException;
	
	boolean updateBySql(String sql,Object[]obs) throws RemoteException;

	Fare getById(int id) throws RemoteException;

	int insert(Fare fare) throws RemoteException;

	boolean update(Fare fare) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
