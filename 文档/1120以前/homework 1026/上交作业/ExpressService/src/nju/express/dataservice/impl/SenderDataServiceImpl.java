package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.SenderDataService;
import nju.express.vo.Sender;

public class SenderDataServiceImpl extends UnicastRemoteObject implements SenderDataService{

	protected SenderDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Sender sender) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sender find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
