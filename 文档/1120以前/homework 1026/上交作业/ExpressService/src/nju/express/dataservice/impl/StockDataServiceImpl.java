package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.StockDataService;
import nju.express.vo.Stock;

public class StockDataServiceImpl extends UnicastRemoteObject implements StockDataService{

	protected StockDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Stock stock) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stock search(String time1, String time2) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
