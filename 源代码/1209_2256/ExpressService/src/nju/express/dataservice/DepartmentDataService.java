package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Department;

public interface DepartmentDataService extends Remote {
	Vector<Department> getAll() throws RemoteException;

	Vector<Department> getBySql(String sql, Object[] obs) throws RemoteException;

	Department getById(int id) throws RemoteException;

	int insert(Department department) throws RemoteException;

	boolean update(Department department) throws RemoteException;

	boolean delete(int id) throws RemoteException;

	Vector<Department> getDepartmentOfType(int type) throws RemoteException;
}
