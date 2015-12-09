package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.User;

/**
 * User���ݷ���ӿ�
 */
public interface UserDataService extends  Remote{
	/**
	 * @param name
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	User getUser(String name, String password) throws RemoteException;
	/**
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	User getById(int id) throws RemoteException;
	
	
	Vector<User> getUserOfJob(int job_id_fk) throws RemoteException;
	/**
	 * @return
	 * @throws RemoteException
	 */
	Vector<User> getAll() throws RemoteException;
	/**
	 * @param user
	 * @return user��id
	 * @throws RemoteException
	 */
	int insert(User user) throws RemoteException;
	/**
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	int update(User user) throws RemoteException;
	
	boolean delete(int id) throws RemoteException;
	
}
