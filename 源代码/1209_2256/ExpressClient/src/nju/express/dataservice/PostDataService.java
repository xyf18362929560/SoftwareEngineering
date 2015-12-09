package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Post;

public interface PostDataService extends Remote {
	Vector<Post> getList() throws RemoteException;

	Vector<Post> getBySql(String sql, Object[] obs) throws RemoteException;

	Post getById(int id) throws RemoteException;

	int insert(Post post) throws RemoteException;

	boolean update(Post post) throws RemoteException;

	boolean update_fk_id(String sql, Object[] obs) throws RemoteException;

	boolean delete(int id) throws RemoteException;

	Vector<Post> getListByLike(String columnname, Object value) throws RemoteException;

	Vector<Post> getListBySql(String sql) throws RemoteException;

	Vector<Post> getListByCondition(String columnname, Object value) throws RemoteException;
}
