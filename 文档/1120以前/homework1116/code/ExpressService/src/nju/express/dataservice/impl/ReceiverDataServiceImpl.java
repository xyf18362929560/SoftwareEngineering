package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.ReceiverDataService;
import nju.express.po.Receiver;
import nju.express.util.DAO;

public class ReceiverDataServiceImpl extends UnicastRemoteObject implements ReceiverDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996771970730703557L;

	public ReceiverDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<Receiver> getAll() throws RemoteException {
		Vector<Receiver> receivers=DAO.getList(Receiver.class);
		return receivers;
	}

	@Override
	public Receiver getById(int id) throws RemoteException {
		Receiver receiver=(Receiver) DAO.getObById(Receiver.class, id);
		return receiver;
	}

	@Override
	public int insert(Receiver receiver) throws RemoteException {
		
		return DAO.insertGetGeneratedKey(receiver);
	}

	@Override
	public int update(Receiver receiver) throws RemoteException {
		DAO.update(receiver, receiver.getId());
		return receiver.getId();
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Receiver.class, id);
	}

}
