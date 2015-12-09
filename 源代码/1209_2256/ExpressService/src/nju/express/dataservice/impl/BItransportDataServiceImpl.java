package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.BItransportDataService;
import nju.express.po.BItransport;
import nju.express.util.DAO;

public class BItransportDataServiceImpl extends UnicastRemoteObject implements BItransportDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038486570106675314L;

	public BItransportDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<BItransport> getAll() throws RemoteException {
		return DAO.getList(BItransport.class);
	}

	@Override
	public Vector<BItransport> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(BItransport.class, sql, obs);
	}

	@Override
	public BItransport getById(int id) throws RemoteException {
		return (BItransport) DAO.getObById(BItransport.class, id);
	}

	@Override
	public int insert(BItransport bItransport) throws RemoteException {
		return DAO.insertGetGeneratedKey(bItransport);
	}

	@Override
	public boolean update(BItransport bItransport) throws RemoteException {
		return DAO.update(bItransport, bItransport.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(BItransport.class, id);
	}

}
