package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.StockDataService;
import nju.express.po.Stock;
import nju.express.util.DAO;

public class StockDataServiceImpl extends UnicastRemoteObject implements StockDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5068177551127668604L;

	public StockDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Vector<Stock> getAll() throws RemoteException {
		return DAO.getList(Stock.class);
	}

	@Override
	public Vector<Stock> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Stock.class, sql, obs);
	}

	@Override
	public Stock getById(int id) throws RemoteException {
		return (Stock) DAO.getObById(Stock.class, id);
	}

	@Override
	public int insert(Stock stock) throws RemoteException {
		return DAO.insertGetGeneratedKey(stock);
	}

	@Override
	public boolean update(Stock stock) throws RemoteException {
		return DAO.update(stock, stock.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Stock.class, id);
	}

}
