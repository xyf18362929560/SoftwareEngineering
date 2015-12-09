package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.IItransport;

public interface IItransportDataService extends Remote {
	Vector<IItransport> getAll() throws RemoteException;

	Vector<IItransport> getBySql(String sql, Object[] obs) throws RemoteException;

	IItransport getById(int id) throws RemoteException;

	int insert(IItransport iItransport) throws RemoteException;

	boolean update(IItransport iItransport) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
