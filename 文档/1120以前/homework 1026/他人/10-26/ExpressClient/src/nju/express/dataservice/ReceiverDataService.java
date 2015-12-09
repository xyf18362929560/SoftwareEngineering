package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Receiver;

public interface ReceiverDataService extends Remote{
	void insert(Receiver receiver) throws RemoteException;
	Receiver find(int id) throws RemoteException;
	Receiver find(String name) throws RemoteException;
}
