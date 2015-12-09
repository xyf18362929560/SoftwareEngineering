package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.DriverDataService;
import nju.express.po.Driver;
import nju.express.util.DAO;

public class DriverDataServiceImpl extends UnicastRemoteObject implements DriverDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7041895744871477617L;

	public DriverDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Vector<Driver> getAll() throws RemoteException {
		return DAO.getList(Driver.class);

	}

	@Override
	public Driver getById(int id) throws RemoteException {
		return (Driver) DAO.getObById(Driver.class, id);
	}

	@Override
	public int insert(Driver driver) throws RemoteException {
		return DAO.insertGetGeneratedKey(driver);
	}

	@Override
	public boolean update(Driver driver) throws RemoteException {
		return DAO.update(driver, driver.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Driver.class, id);
	}

	@Override
	public Vector<Driver> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Driver.class, sql, obs);
	}

}
