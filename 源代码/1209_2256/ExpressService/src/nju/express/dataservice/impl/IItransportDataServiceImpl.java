package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.IItransportDataService;
import nju.express.po.IItransport;
import nju.express.util.DAO;

public class IItransportDataServiceImpl extends UnicastRemoteObject implements IItransportDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7908964386126217237L;

	public IItransportDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<IItransport> getAll() throws RemoteException {
		return DAO.getList(IItransport.class);
	}

	@Override
	public Vector<IItransport> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(IItransport.class, sql, obs);
	}

	@Override
	public IItransport getById(int id) throws RemoteException {
		return (IItransport) DAO.getObById(IItransport.class, id);
	}

	@Override
	public int insert(IItransport iItransport) throws RemoteException {
		return DAO.insertGetGeneratedKey(iItransport);
	}

	@Override
	public boolean update(IItransport iItransport) throws RemoteException {
		return DAO.update(iItransport, iItransport.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(IItransport.class, id);
	}

}
