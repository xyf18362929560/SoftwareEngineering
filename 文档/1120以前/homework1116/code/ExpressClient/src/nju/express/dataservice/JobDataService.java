package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.vo.Job;

public interface JobDataService extends Remote {
	Vector<Job> getAll() throws RemoteException;

	Job getById(int id) throws RemoteException;

	int insert(Job job) throws RemoteException;

	int update(Job job) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
