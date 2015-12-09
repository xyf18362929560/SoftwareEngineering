package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Receiver;

public interface ReceiverDataService extends Remote {
	Vector<Receiver> getAll() throws RemoteException;
	Receiver getById(int id) throws RemoteException;
	int insert(Receiver receiver) throws RemoteException;
	int update(Receiver receiver) throws RemoteException;
	boolean delete(int id) throws RemoteException;
}
