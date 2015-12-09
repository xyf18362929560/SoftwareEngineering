package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.User;

/**
 * User���ݷ���ӿ�
 */
public interface UserDataService extends Remote{
	User getUser(String name, String password) throws RemoteException;
	User getUser(int id) throws RemoteException;
	void insert(User user) throws RemoteException;
	void delete(User user) throws RemoteException;
	void update(User user) throws RemoteException;
}
