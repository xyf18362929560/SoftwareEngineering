package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.JobDataService;
import nju.express.vo.Job;

public class JobDataServiceImpl extends UnicastRemoteObject implements JobDataService{

	protected JobDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Job find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
