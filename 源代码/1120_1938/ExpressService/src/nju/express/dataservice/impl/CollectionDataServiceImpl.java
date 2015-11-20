package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.CollectionDataService;
import nju.express.po.Collection;
import nju.express.util.DAO;

public class CollectionDataServiceImpl extends UnicastRemoteObject implements CollectionDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3315821957061427317L;

	public CollectionDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<Collection> getAll() throws RemoteException {
		
		return DAO.getList(Collection.class);
	}

	@Override
	public Collection getById(int id) throws RemoteException {
	
		return (Collection) DAO.getObById(Collection.class, id);
	}

	@Override
	public int insert(Collection collection) throws RemoteException {
	
		return DAO.insertGetGeneratedKey(collection);
	}

	@Override
	public boolean update(Collection collection) throws RemoteException {
		
		return DAO.update(collection, collection.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Collection.class, id);
	}

}
