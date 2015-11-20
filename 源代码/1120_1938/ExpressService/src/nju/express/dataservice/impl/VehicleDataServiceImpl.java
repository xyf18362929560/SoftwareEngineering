package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.VehicleDataService;
import nju.express.po.Post;
import nju.express.po.Vehicle;
import nju.express.util.DAO;

public class VehicleDataServiceImpl extends UnicastRemoteObject implements VehicleDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3655119551817470400L;

	public VehicleDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<Vehicle> getAll() throws RemoteException {
		String sql="select * from vehicle order by vehicle_num ";
		return DAO.getListBySql(Vehicle.class, sql);
	}

	@Override
	public Vehicle getById(int id) throws RemoteException {
		return (Vehicle) DAO.getObById(Vehicle.class, id);
	}

	@Override
	public int insert(Vehicle vehicle) throws RemoteException {
		return DAO.insertGetGeneratedKey(vehicle);
	}

	@Override
	public boolean update(Vehicle vehicle) throws RemoteException {
		
		return DAO.update(vehicle, vehicle.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Vehicle.class, id);
	}

	@Override
	public Vector<Vehicle> getByCondition(String columnname, Object value) throws RemoteException {
		Vector<Vehicle> vehicles = DAO.getListByCondition(Post.class, columnname, value);
		return vehicles;
	}

	@Override
	public Vector<Vehicle> getListBySql(String sql) throws RemoteException {
		Vector<Vehicle> vehicles = DAO.getListBySql(Vehicle.class, sql);
		return vehicles;
	}

}
