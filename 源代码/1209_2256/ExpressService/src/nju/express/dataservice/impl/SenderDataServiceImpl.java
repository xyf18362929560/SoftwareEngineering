package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.SenderDataService;
import nju.express.po.Sender;
import nju.express.util.DAO;

public class SenderDataServiceImpl extends UnicastRemoteObject implements SenderDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6730119679915437858L;

	public SenderDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Sender getById(int id) throws RemoteException {
		Sender sender = (Sender) DAO.getObById(Sender.class, id);
		return sender;
	}

	@Override
	public Vector<Sender> getAll() throws RemoteException {
		Vector<Sender> senders = DAO.getList(Sender.class);
		return senders;
	}

	@Override
	public int insert(Sender sender) throws RemoteException {

		return DAO.insertGetGeneratedKey(sender);
	}

	@Override
	public boolean update(Sender sender) throws RemoteException {

		return DAO.update(sender, sender.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Sender.class, id);
	}

}
