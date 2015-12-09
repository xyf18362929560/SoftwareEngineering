package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.VehicleDataService;
import nju.express.vo.Vehicle;

public class VehicleDataServiceImpl extends UnicastRemoteObject implements VehicleDataService{

	protected VehicleDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vehicle getVehicle(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle getVehicle(String lience) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
