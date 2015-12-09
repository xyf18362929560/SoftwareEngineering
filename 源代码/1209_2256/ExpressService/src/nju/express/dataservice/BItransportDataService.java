package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.BItransport;

public interface BItransportDataService extends Remote {
	Vector<BItransport> getAll() throws RemoteException;

	Vector<BItransport> getBySql(String sql, Object[] obs) throws RemoteException;

	BItransport getById(int id) throws RemoteException;

	int insert(BItransport bItransport) throws RemoteException;

	boolean update(BItransport bItransport) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
