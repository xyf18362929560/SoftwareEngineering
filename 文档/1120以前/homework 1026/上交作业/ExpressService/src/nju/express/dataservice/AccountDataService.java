package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.express.vo.Collection;
import nju.express.vo.Payment;

public interface AccountDataService extends Remote {
	List<Collection> checkCollection(int id, String account_name, String account_startdate, String account_enddate) throws RemoteException;

	List<Payment> checkPayment(int id, String account_name, String account_startdate, String account_enddate)throws RemoteException;

	double checkamount(int id, String account_name, String account_startdate, String account_enddate)throws RemoteException;
}
