package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.FareDataService;
import nju.express.po.Fare;
import nju.express.util.DAO;

public class FareDataServiceImpl extends UnicastRemoteObject implements  FareDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -837691885096752483L;

	public FareDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Fare> getAll() throws RemoteException {
		return DAO.getList(Fare.class);
	}

	@Override
	public Vector<Fare> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Fare.class, sql, obs);
	}

	@Override
	public Fare getById(int id) throws RemoteException {
		return (Fare) DAO.getObById(Fare.class, id);
	}

	@Override
	public int insert(Fare fare) throws RemoteException {
		return DAO.insertGetGeneratedKey(fare);
	}

	@Override
	public boolean update(Fare fare) throws RemoteException {
		return DAO.update(fare, fare.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Fare.class, id);
	}

	@Override
	public boolean updateBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.updateBySql(Fare.class, sql, obs);
	}

}
