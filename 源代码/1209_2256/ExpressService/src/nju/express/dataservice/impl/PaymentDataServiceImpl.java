package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.PaymentDataService;
import nju.express.po.Payment;
import nju.express.util.DAO;

public class PaymentDataServiceImpl extends UnicastRemoteObject implements PaymentDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -779835097594054597L;

	public PaymentDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Payment> getAll() throws RemoteException {
		return DAO.getList(Payment.class);
	}

	@Override
	public Vector<Payment> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Payment.class, sql, obs);
	}

	@Override
	public Payment getById(int id) throws RemoteException {
		return (Payment) DAO.getObById(Payment.class, id);
	}

	@Override
	public int insert(Payment payment) throws RemoteException {
		return DAO.insertGetGeneratedKey(payment);
	}

	@Override
	public boolean update(Payment payment) throws RemoteException {
		return DAO.update(payment, payment.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Payment.class, id);
	}

}
