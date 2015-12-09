package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.PaymentDataService;
import nju.express.vo.Payment;

public class PaymentDataServiceImpl extends UnicastRemoteObject implements PaymentDataService{

	protected PaymentDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Payment payment) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double statistic(String starttime, String endtime)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
