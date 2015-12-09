package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.RecipientDataService;
import nju.express.vo.Recipient;

public class RecipientDataServiceImpl extends UnicastRemoteObject implements RecipientDataService{

	protected RecipientDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Recipient recipient) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Recipient find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipient find(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
