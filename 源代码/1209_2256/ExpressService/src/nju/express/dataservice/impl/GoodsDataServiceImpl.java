package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.GoodsDataService;
import nju.express.po.Goods;
import nju.express.util.DAO;

public class GoodsDataServiceImpl extends UnicastRemoteObject implements GoodsDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6671510390827312240L;

	public GoodsDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Vector<Goods> getAll() throws RemoteException {
		@SuppressWarnings("unchecked")
		Vector<Goods> goodss = DAO.getList(Goods.class);
		return goodss;
	}

	@Override
	public Goods getById(int id) throws RemoteException {
		Goods goods = (Goods) DAO.getObById(Goods.class, id);
		return goods;
	}

	@Override
	public int insert(Goods goods) throws RemoteException {

		return DAO.insertGetGeneratedKey(goods);
	}

	@Override
	public boolean update(Goods goods) throws RemoteException {

		return DAO.update(goods, goods.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Goods.class, id);
	}

}
