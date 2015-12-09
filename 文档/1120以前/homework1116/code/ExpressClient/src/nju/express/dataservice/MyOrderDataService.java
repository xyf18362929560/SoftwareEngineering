package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.vo.MyOrder;

public interface MyOrderDataService extends Remote {
	Vector<MyOrder> getList() throws RemoteException;

	MyOrder getById(int id) throws RemoteException;

	int insert(MyOrder order) throws RemoteException;

	boolean update(MyOrder order,String columnname,Object value) throws RemoteException;

	boolean delete(int id) throws RemoteException;

	Vector<MyOrder> getListBySql(String sql) throws RemoteException;

}
