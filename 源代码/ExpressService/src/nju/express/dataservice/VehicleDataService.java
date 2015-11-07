package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.po.Vehicle;

public interface VehicleDataService extends Remote {
	Vector<Vehicle> getAll() throws RemoteException;

	Vector<Vehicle> getByCondition(String columnname, Object value) throws RemoteException;

	Vector<Vehicle> getListBySql(String sql) throws RemoteException;

	Vehicle getById(int id) throws RemoteException;

	int insert(Vehicle vehicle) throws RemoteException;

	int update(Vehicle vehicle) throws RemoteException;

	boolean delete(int id) throws RemoteException;
}
