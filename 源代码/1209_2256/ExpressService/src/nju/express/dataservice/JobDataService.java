package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Job;

public interface JobDataService extends Remote {
	Vector<Job> getAll() throws RemoteException;
	
	boolean updateBySql(String sql,Object[]obs) throws RemoteException;

	Job getById(int id) throws RemoteException;

	int insert(Job job) throws RemoteException;

	boolean update(Job job) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
