package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.BItransport;

public interface BItransportDataService extends Remote {
	void addBItransport(BItransport bitransport) throws RemoteException;

	void deleteBItransport(int id)throws RemoteException;

	void updateBItransport(int id, BItransport bitransport)throws RemoteException;

	BItransport getBItransport(int id)throws RemoteException;
}
