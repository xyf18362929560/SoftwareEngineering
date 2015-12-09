package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.vo.Sender;

public interface SenderDataService extends Remote {
	Sender getById(int id) throws RemoteException;
	Vector<Sender> getAll() throws RemoteException;
	int insert(Sender sender) throws RemoteException;
	int update(Sender sender) throws RemoteException;
	boolean delete(int id) throws RemoteException;
}
