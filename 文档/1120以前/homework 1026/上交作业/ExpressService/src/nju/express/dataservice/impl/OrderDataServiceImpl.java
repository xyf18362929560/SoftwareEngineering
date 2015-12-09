package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.OrderDataService;
import nju.express.vo.Order;

public class OrderDataServiceImpl extends UnicastRemoteObject implements OrderDataService{

	protected OrderDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Order order) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void change(int id, Order order) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
