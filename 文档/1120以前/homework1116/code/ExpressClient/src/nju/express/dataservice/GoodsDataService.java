package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.vo.Goods;

public interface GoodsDataService extends Remote {
	Vector<Goods> getAll() throws RemoteException;

	Goods getById(int id) throws RemoteException;

	int insert(Goods goods) throws RemoteException;

	int update(Goods goods) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
