package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Payment;

public interface PaymentDataService extends Remote {
	Vector<Payment> getAll() throws RemoteException;

	Vector<Payment> getBySql(String sql, Object[] obs) throws RemoteException;

	Payment getById(int id) throws RemoteException;

	int insert(Payment payment) throws RemoteException;

	boolean update(Payment payment) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
