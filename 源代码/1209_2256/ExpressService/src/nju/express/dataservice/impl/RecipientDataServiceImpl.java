package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.RecipientDataService;
import nju.express.po.Recipient;
import nju.express.util.DAO;

public class RecipientDataServiceImpl extends UnicastRemoteObject implements RecipientDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -232332425082036891L;

	public RecipientDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Recipient> getAll() throws RemoteException {
		return DAO.getList(Recipient.class);
	}

	@Override
	public Vector<Recipient> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Recipient.class, sql, obs);
	}

	@Override
	public Recipient getById(int id) throws RemoteException {
		return (Recipient) DAO.getObById(Recipient.class, id);
	}

	@Override
	public int insert(Recipient recipient) throws RemoteException {
		return DAO.insertGetGeneratedKey(recipient);
	}

	@Override
	public boolean update(Recipient recipientS) throws RemoteException {
		return DAO.update(recipientS, recipientS.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Recipient.class, id);
	}

}
