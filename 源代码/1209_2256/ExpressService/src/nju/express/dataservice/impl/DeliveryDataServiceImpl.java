package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.DeliveryDataService;
import nju.express.po.Delivery;
import nju.express.util.DAO;

public class DeliveryDataServiceImpl  extends UnicastRemoteObject implements DeliveryDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -152555045261772710L;

	public DeliveryDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Delivery> getAll() throws RemoteException {
		return DAO.getList(Delivery.class);
	}

	@Override
	public Vector<Delivery> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Delivery.class, sql, obs);
	}

	@Override
	public Delivery getById(int id) throws RemoteException {
		return (Delivery) DAO.getObById(Delivery.class, id);
	}

	@Override
	public int insert(Delivery delivery) throws RemoteException {
		return DAO.insertGetGeneratedKey(delivery);
	}

	@Override
	public boolean update(Delivery delivery) throws RemoteException {
		return DAO.update(delivery, delivery.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Delivery.class, id);
	}
	
}
