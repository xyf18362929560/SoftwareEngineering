package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.User;

/**
 * User数据服务接口
 */
public interface UserDataService extends  Remote{
	User getUser(String name, String password) throws RemoteException;
	User getUser(int id) throws RemoteException;
}
