package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Stock;

public interface StockDataService extends Remote {
	Vector<Stock> getAll() throws RemoteException;

	Vector<Stock> getBySql(String sql, Object[] obs) throws RemoteException;

	Stock getById(int id) throws RemoteException;

	int insert(Stock stock) throws RemoteException;

	boolean update(Stock stock) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
