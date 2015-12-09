package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.ReceiverDataService;
import nju.express.vo.Receiver;

public class ReceiverDataServiceImpl extends UnicastRemoteObject implements ReceiverDataService{

	protected ReceiverDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Receiver receiver) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Receiver find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receiver find(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
