package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.vo.Department;

public interface DepartmentDataService extends Remote {
	Vector<Department> getAll() throws RemoteException;

	Department getById(int id) throws RemoteException;

	int insert(Department department) throws RemoteException;

	int update(Department department) throws RemoteException;

	boolean delete(int id) throws RemoteException;
	
	Vector<Department> getDepartmentOfType(int type) throws RemoteException;
}
