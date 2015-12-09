package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import nju.express.dataservice.MyOrderDataService;
import nju.express.po.MyOrder;
import nju.express.util.ConnectionFactory;
import nju.express.util.DAO;

public class MyOrderDataServiceImpl extends UnicastRemoteObject implements MyOrderDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1742358249852503833L;

	public MyOrderDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Vector<MyOrder> getList() throws RemoteException {
		Vector<MyOrder> orders = DAO.getList(MyOrder.class);
		return orders;
	}

	@Override
	public MyOrder getById(int id) throws RemoteException {
		MyOrder order = (MyOrder) DAO.getObById(MyOrder.class, id);
		return order;
	}

	@Override
	public int insert(MyOrder order) throws RemoteException {
		int id = 0;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=" insert into myorder (post_id_fk) values (?) ";
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getPost_id_fk());
			int a = ps.executeUpdate();

			if (a > 0) {

				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeRes(conn, ps,rs);
		}
		return id;
	}

	@Override
	public boolean update(MyOrder order,String columnname,Object value) throws RemoteException {
		boolean b = false;
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		PreparedStatement ps = null;
		
		String sql=" update 'order' set "+columnname+ " = ? where id = "+order.getId();
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, value);
			int a = ps.executeUpdate();

			
			if (a > 0) {
				b = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeRes(conn, ps);
		}
		return b;
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(MyOrder.class, id);
	}

	@Override
	public Vector<MyOrder> getListBySql(String sql) throws RemoteException {
		Vector<MyOrder> orders = DAO.getListBySql(MyOrder.class, sql);
		return orders;
	}

}
