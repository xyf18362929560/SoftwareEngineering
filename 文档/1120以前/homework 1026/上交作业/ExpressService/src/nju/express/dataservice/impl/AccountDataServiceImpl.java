package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.express.dataservice.AccountDataService;
import nju.express.vo.Account;
import nju.express.vo.Collection;
import nju.express.vo.Payment;

public class AccountDataServiceImpl extends UnicastRemoteObject implements AccountDataService{

	public AccountDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Collection> checkCollection(int id, String account_name, String account_startdate,
			String account_enddate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> checkPayment(int id, String account_name, String account_startdate, String account_enddate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double checkamount(int id, String account_name, String account_startdate, String account_enddate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
